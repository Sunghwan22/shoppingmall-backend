package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class CartItemIdsDto {
    private List<Long> cartItemsId;

    public CartItemIdsDto() {
    }

    public CartItemIdsDto(List<Long> cartItemsId) {
        this.cartItemsId = cartItemsId;
    }

    public List<Long> getCartItemsId() {
        return cartItemsId;
    }
}
