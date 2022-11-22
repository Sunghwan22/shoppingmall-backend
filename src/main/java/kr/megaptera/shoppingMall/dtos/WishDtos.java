package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class WishDtos {
    private List<WishDto> wishes;

    public WishDtos(List<WishDto> wishDtos) {
        this.wishes = wishDtos;
    }

    public List<WishDto> getWishes() {
        return wishes;
    }
}
