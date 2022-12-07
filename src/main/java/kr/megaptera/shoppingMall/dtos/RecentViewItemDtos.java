package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class RecentViewItemDtos {
    private List<ProductDto> recentViewItems;

    public RecentViewItemDtos() {
    }

    public RecentViewItemDtos(List<ProductDto> recentViewItems) {
        this.recentViewItems = recentViewItems;
    }

    public List<ProductDto> getRecentViewItems() {
        return recentViewItems;
    }
}
