package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.RecentViewItemDtos;
import kr.megaptera.shoppingMall.services.GetRecentViewItemsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecentViewItemController {
    private final GetRecentViewItemsService getRecentViewItemsService;

    public RecentViewItemController(GetRecentViewItemsService getRecentViewItemsService) {
        this.getRecentViewItemsService = getRecentViewItemsService;
    }

    @GetMapping("/recentViewItems")
    public RecentViewItemDtos recentViewItemList(
        @RequestParam("productIds") String productIds
    ) {
        return getRecentViewItemsService.getRecentViewItems(productIds);
    }
}
