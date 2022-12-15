package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.WriteableReviewProductsDto;
import kr.megaptera.shoppingMall.models.Order;
import kr.megaptera.shoppingMall.repositoies.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetWriteableReviewOrdersServiceTest {
    GetWriteableReviewOrdersService getWriteableReviewOrderService;
    OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        getWriteableReviewOrderService = new GetWriteableReviewOrdersService(orderRepository);
    }

    @Test
    void WriteableReviewOrders() {
        int page = 1;

        List<Order> orderList = List.of(
            Order.fake(Order.NO_REVIEW),
            Order.fake(Order.REVIEW_WRITING_COMPLETE)
        );

        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(page - 1, 5, sort);

        given(orderRepository.findAllByUserId(1L, pageable)).willReturn(
            new PageImpl<>(orderList, pageable, 2)
        );

        WriteableReviewProductsDto writeableReviewProductsDto=
            getWriteableReviewOrderService.getOrders(1L, 1);

        assertThat(writeableReviewProductsDto.getOrders().size()).isEqualTo(1);
        assertThat(writeableReviewProductsDto.getOrders().get(0).getName()).isEqualTo("아이폰 14");
        assertThat(writeableReviewProductsDto.getOrders().get(0).getPhoneNumber()).isEqualTo("010-3144-7938");
        assertThat(writeableReviewProductsDto.getPages()).isEqualTo(1);
    }
}
