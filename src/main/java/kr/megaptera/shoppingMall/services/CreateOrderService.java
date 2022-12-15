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
    private final KakaoPay kakaoPay;
    private final CartItemRepository cartItemRepository;


    public CreateOrderService(
        ProductRepository productRepository,
        OrderRepository orderRepository,
        KakaoPay kakaoPay,
        CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.kakaoPay = kakaoPay;
        this.cartItemRepository = cartItemRepository;
    }

    public String createOrder(Long userId, OrderRequestDto orderRequestDto) {
        String alternativeImage = "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/NO+IMAGE.gif";

        List<OrderProductDto> orderProducts = orderRequestDto.getOrderProducts();

        for (OrderProductDto orderProductDto : orderProducts) {
            Product product = productRepository.findById(orderProductDto.getProductId())
                .orElseThrow(ProductNotFound::new);

            if (product.stock() < orderProductDto.getQuantity()) {
                throw new SoldOutException();
            }

            Address address = new Address(
                orderRequestDto.getAddress().getZoneCode(),
                orderRequestDto.getAddress().getFullAddress(),
                orderRequestDto.getAddress().getJibunAddress(),
                orderRequestDto.getAddress().getDetailAddress()
            );

            Order order = new Order(
                userId,
                product.id(),
                orderProductDto.getName(),
                orderRequestDto.getPhoneNumber(),
                orderProductDto.getImage(),
                address,
                orderProductDto.getQuantity(),
                orderProductDto.getTotalPayment(),
                orderRequestDto.getDeliveryRequest(),
                orderProductDto.getDescription(),
                Order.NO_REVIEW
            );

            orderRepository.save(order);

            product.addPurchaseNumber();
            product.reduceProduct(orderProductDto.getQuantity());
//
//            for (OrderProductDto orderProduct : orderProducts) {
//                if (orderProduct.getId() != null) {
//                    cartItemRepository.deleteById(orderProduct.getId());
//                }
//            }
            if(orderProductDto.getId() != null) {
                cartItemRepository.deleteById(orderProductDto.getId());
            }
        }

        String productName = orderProducts.get(0).getName();
        Long quantity = orderProducts.get(0).getQuantity();

        if (orderProducts.size() >= 2) {
            productName =
                orderProducts.get(0).getName() + " 외 " +
                    "" + (orderProducts.size() - 1) + "건";

            for (OrderProductDto orderProduct : orderProducts) {
                quantity = 0L;
                quantity += orderProduct.getQuantity();
            }
        }

        String orderId = UUID.randomUUID().toString();

        return kakaoPay.kakaoPayReady(
            orderId,
            userId,
            productName,
            quantity,
            orderRequestDto.getTotalOrderPayment(),
            orderProducts
        );
    }
}
