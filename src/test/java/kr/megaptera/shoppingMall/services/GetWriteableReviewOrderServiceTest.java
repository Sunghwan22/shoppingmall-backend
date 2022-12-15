package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.OrderDto;
import kr.megaptera.shoppingMall.models.Order;
import kr.megaptera.shoppingMall.repositoies.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetWriteableReviewOrderServiceTest {
    OrderRepository orderRepository;
    GetWriteableReviewOrderService getWriteableReviewOrderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        getWriteableReviewOrderService = new GetWriteableReviewOrderService(orderRepository);
    }

    @Test
    void detail() {
        Order order = Order.fake(Order.NO_REVIEW);

        given(orderRepository.findById(1L))
            .willReturn(Optional.of(order));

        OrderDto orderDto = getWriteableReviewOrderService.getOrder(1L);

        assertThat(orderDto.getName()).isEqualTo("아이폰 14");
        assertThat(orderDto.getDescription()).isEqualTo("옵션명블랙");
        assertThat(orderDto.getImageUrl()).isEqualTo("imageUrl");
    }
}