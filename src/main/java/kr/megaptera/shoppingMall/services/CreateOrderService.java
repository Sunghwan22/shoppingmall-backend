package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.OrderProductDto;
import kr.megaptera.shoppingMall.dtos.OrderRequestDto;
import kr.megaptera.shoppingMall.exceptions.ProductNotFound;
import kr.megaptera.shoppingMall.exceptions.SoldOutException;
import kr.megaptera.shoppingMall.models.Address;
import kr.megaptera.shoppingMall.models.Order;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.OrderRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.utils.KakaoPay;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CreateOrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final KakaoPay kakaoPay;

    public CreateOrderService(
        ProductRepository productRepository,
        OrderRepository orderRepository,
        CartItemRepository cartItemRepository,
        KakaoPay kakaoPay) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.kakaoPay = kakaoPay;
    }

    public String createOrder(Long userId, OrderRequestDto orderRequestDto) {
        String alternativeImage = "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/NO+IMAGE.gif";

        List<OrderProductDto> orderProducts = orderRequestDto.getOrderProducts();

        for (int i = 0; i < orderProducts.size(); i += 1) {
            Product product = productRepository.findById(orderProducts.get(i).getProductId())
                .orElseThrow(ProductNotFound::new);

            if (product.stock() < orderProducts.get(i).getQuantity()) {
                throw new SoldOutException();
            }

            Address address = new Address(
                orderRequestDto.getAddress().getZoneCode(),
                orderRequestDto.getAddress().getFullAddress(),
                orderRequestDto.getAddress().getJibunAddress()
            );

            Order order = new Order(
                userId,
                orderProducts.get(i).getName(),
                orderRequestDto.getPhoneNumber(),
                orderProducts.get(i).getImage(),
                address,
                orderProducts.get(i).getQuantity(),
                orderProducts.get(i).getTotalPayment(),
                orderRequestDto.getDeliveryRequest(),
                orderRequestDto.getDetailAddress(),
                orderProducts.get(i).getOptionDescription()
            );

            orderRepository.save(order);

            product.addPurchaseNumber();
            product.reduceProduct(orderProducts.get(i).getQuantity());

            if(orderProducts.get(i).getId() != null) {
                cartItemRepository.deleteById(orderProducts.get(i).getId());
            }
        }

        String productName = orderProducts.get(0).getName();
        Long quantity = orderProducts.get(0).getQuantity();

        if (orderProducts.size() >= 2) {
            productName =
                orderProducts.get(0).getName() + " 외 " +
                    "" + (orderProducts.size() - 1) + "건";

            for (int i = 0; i < orderProducts.size(); i += 1) {
                quantity = 0L;
                quantity += orderProducts.get(i).getQuantity();
            }
        }

        String orderId = UUID.randomUUID().toString();

        return kakaoPay.kakaoPayReady(
            orderId,
            userId,
            productName,
            quantity,
            orderRequestDto.getTotalOrderPayment()
        );
    }
}
