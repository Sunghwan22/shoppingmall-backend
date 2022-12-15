package kr.megaptera.shoppingMall.dtos;

import kr.megaptera.shoppingMall.models.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public class WriteableReviewProductsDto {
    private int pages;
    private List<OrderDto> orderDtos;

    public WriteableReviewProductsDto() {
    }

    public WriteableReviewProductsDto(List<OrderDto> orderDtos, int pages) {
        this.orderDtos = orderDtos;
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    public List<OrderDto> getOrders() {
        return orderDtos;
    }
}
