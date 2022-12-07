package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.WishDto;
import kr.megaptera.shoppingMall.dtos.WishDtos;
import kr.megaptera.shoppingMall.services.CreateWishService;
import kr.megaptera.shoppingMall.services.GetProductWishesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wishes")
public class WishController {
    private final CreateWishService createWishService;
    private final GetProductWishesService getProductWishesService;

    public WishController(
        CreateWishService createWishService,
        GetProductWishesService getProductWishesService) {
        this.createWishService = createWishService;
        this.getProductWishesService = getProductWishesService;
    }

    @PostMapping("/products/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public WishDtos wishes(
        @RequestAttribute("userId") Long userId,
        @PathVariable("id") Long productId
    ) {
        List<WishDto> wishDtos = createWishService.create(productId, userId);

        return new WishDtos(wishDtos);
    }

    @GetMapping("/products/{id}")
    public WishDtos productWishes(
        @PathVariable("id") Long productId
        ) {
        return getProductWishesService.getProductWishes(productId);
    }
}
