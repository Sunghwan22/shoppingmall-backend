package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class WishItemDtos {
    private List<ProductDto> wishItems;

    public WishItemDtos() {
    }

    public WishItemDtos(List<ProductDto> wishItems) {
        this.wishItems = wishItems;
    }

    public List<ProductDto> getWishItems() {
        return wishItems;
    }
}
