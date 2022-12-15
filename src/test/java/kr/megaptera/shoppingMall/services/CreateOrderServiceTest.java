package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.OrderRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.utils.KakaoPay;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

class CreateOrderServiceTest {
    OrderRepository orderRepository;
    CreateOrderService createOrderService;
    ProductRepository productRepository;
    CartItemRepository cartItemRepository;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        productRepository = mock(ProductRepository.class);
        cartItemRepository = mock(CartItemRepository.class);
        KakaoPay kakaoPay = mock(KakaoPay.class);
        createOrderService = new CreateOrderService(
            productRepository, orderRepository, kakaoPay, cartItemRepository);
    }

//    @Test
//    void createOrderHistory() {
//        Long userId = 1L;
//
//        Product product = Product.fake(1L);
//
//        given(productRepository.findById(1L))
//            .willReturn(Optional.of(product));
//
//        Address address1 = new Address(44637L, "상세주소", "지번 주소");
//
//        OrderRequestDto orderRequestDto = new OrderRequestDto(
//            "조성환", "01031447938", 1L, address1.toDto(), 1L, 33000L, "배송요청사항"
//            , "2층 왼쪽집입니다", "상품 옵션"
//        );
//
//        OrderDto orderDto = createOrderService.createOrder(userId
//            , orderRequestDto);
//
//        assertThat(orderDto.getPhoneNumber()).isEqualTo("01031447938");
//        assertThat(orderDto.getAddress().getFullAddress()).isEqualTo("상세주소");
//        assertThat(orderDto.getOrderPayment()).isEqualTo(33000L);
//        assertThat(orderDto.getDeliveryRequest()).isEqualTo("배송요청사항");
//        assertThat(orderDto.getDetailAddress()).isEqualTo("2층 왼쪽집입니다");
//        assertThat(orderDto.getProductOptionDescription()).isEqualTo("상품 옵션");
//
//        verify(orderRepository).save(any());
//    }

}