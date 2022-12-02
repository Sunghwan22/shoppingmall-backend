package kr.megaptera.shoppingMall.dtos;

import kr.megaptera.shoppingMall.models.Option;

public class requestUpdateCartItemDto {
    private Long addAmount;

    private String description;

    private Long quantity;

    private Long totalPrice;

    public requestUpdateCartItemDto(
        Long addAmount,
        String description,
        Long quantity,
        Long totalPrice) {
        this.addAmount = addAmount;
        this.description = description;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getAddAmount() {
        return addAmount;
    }

    public String getDescription() {
        return description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }
}
