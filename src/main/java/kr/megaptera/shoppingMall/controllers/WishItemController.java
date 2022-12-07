package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.WishItemDtos;
import kr.megaptera.shoppingMall.services.DeleteWishItemService;
import kr.megaptera.shoppingMall.services.GetWishItemsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WishItemController {
    private final GetWishItemsService getWishItemsService;
    private final DeleteWishItemService deleteWishItemService;

    public WishItemController(
        GetWishItemsService getWishItemsService,
        DeleteWishItemService deleteWishItemService) {
        this.getWishItemsService = getWishItemsService;
        this.deleteWishItemService = deleteWishItemService;
    }

    @GetMapping("user/me/wishItems")
    public WishItemDtos wishItems(
        @RequestAttribute("userId") Long userId
    ) {
        List<ProductDto> wishItems = getWishItemsService.getWishItems(userId);

        return new WishItemDtos(wishItems);
    }

    @DeleteMapping("/wishItems/{product_id}")
    public void deleteWishItem(
        @PathVariable("product_id") Long productId,
        @RequestAttribute("userId") Long userId
    ) {
        deleteWishItemService.deleteWishItem(productId,userId);
    }
}
