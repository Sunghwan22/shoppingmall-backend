package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class CartItemDetailDto {
    private CartItemDto cartItemDto;
    private List<OptionDto> optionDtos;
    private Long price;

    public CartItemDetailDto() {
    }

    public CartItemDetailDto(
        CartItemDto cartItemDto,
        List<OptionDto> optionDtos,
        Long price) {
        this.cartItemDto = cartItemDto;
        this.optionDtos = optionDtos;
        this.price = price;
    }

    public CartItemDto getCartItem() {
        return cartItemDto;
    }

    public List<OptionDto> getOptions() {
        return optionDtos;
    }

    public Long getPrice() {
        return price;
    }
}
