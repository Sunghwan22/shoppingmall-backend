package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class CartItemDtos {
    private List<CartItemDto> cartItemDtos;
    private int pages;
    private int totalNumber;

    public CartItemDtos(List<CartItemDto> cartItemDtos, int pages,
                        int totalNumber) {
        this.cartItemDtos = cartItemDtos;
        this.pages = pages;
        this.totalNumber = totalNumber;
    }

    public List<CartItemDto> getCartItems() {
        return cartItemDtos;
    }

    public int getPages() {
        return pages;
    }

    public int getTotalNumber() {
        return totalNumber;
    }
}
