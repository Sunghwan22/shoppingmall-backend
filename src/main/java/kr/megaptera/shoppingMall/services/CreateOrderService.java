package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.OrderDto;
import kr.megaptera.shoppingMall.dtos.OrderRequestDto;
import kr.megaptera.shoppingMall.exceptions.SoldOutException;
import kr.megaptera.shoppingMall.exceptions.ProductNotFound;
import kr.megaptera.shoppingMall.models.Address;
import kr.megaptera.shoppingMall.models.Order;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.repositoies.OrderRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.utils.KakaoPay;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateOrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final KakaoPay kakaoPay;

    public CreateOrderService(
        ProductRepository productRepository,
        OrderRepository orderRepository,
        KakaoPay kakaoPay) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.kakaoPay = kakaoPay;
    }

    public OrderDto createOrder(Long userId, OrderRequestDto orderRequestDto) {
        String alternativeImage = "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/NO+IMAGE.gif";

        Product product = productRepository.findById(orderRequestDto.getProductId())
            .orElseThrow(ProductNotFound::new);

        if (product.stock() < orderRequestDto.getQuantity()) {
            throw new SoldOutException();
        }

        String imageUrl = product.images().stream()
            .filter(ProductImage::getThumbnailImage)
            .findFirst().orElse(new ProductImage(alternativeImage, true))
            .getUrl();

        Address address = new Address(
            orderRequestDto.getAddress().getZoneCode(),
            orderRequestDto.getAddress().getFullAddress(),
            orderRequestDto.getAddress().getJibunAddress()
        );

        Order order = new Order(
            userId,
            orderRequestDto.getName(),
            orderRequestDto.getPhoneNumber(),
            imageUrl,
            address,
            orderRequestDto.getQuantity(),
            orderRequestDto.getOrderPayment(),
            orderRequestDto.getDeliveryRequest(),
            orderRequestDto.getDetailAddress(),
            orderRequestDto.getProductOptionDescription()
        );

        Order savedOrder = orderRepository.save(order);

        String kakaoPayUrl = kakaoPay.kakaoPayReady(
            savedOrder.id(),
            userId,
            product.name(),
            orderRequestDto.getQuantity(),
            orderRequestDto.getOrderPayment()
        );

        product.addPurchaseNumber();
        product.reduceProduct(orderRequestDto.getQuantity());

        return order.toDto(kakaoPayUrl);
    }
}
