package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class CartItemDtos {
    private List<CartItemDto> cartItemDtos;
    private int totalNumber;

    public CartItemDtos(
        List<CartItemDto> cartItemDtos,
        int totalNumber) {
        this.cartItemDtos = cartItemDtos;
        this.totalNumber = totalNumber;
    }

    public List<CartItemDto> getCartItems() {
        return cartItemDtos;
    }

    public int getTotalNumber() {
        return totalNumber;
    }
}
