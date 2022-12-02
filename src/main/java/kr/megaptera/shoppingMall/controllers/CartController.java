package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.CartItemDetailDto;
import kr.megaptera.shoppingMall.dtos.CartItemDto;
import kr.megaptera.shoppingMall.dtos.CartItemDtos;
import kr.megaptera.shoppingMall.dtos.CartItemIdsDto;
import kr.megaptera.shoppingMall.dtos.CreateCartItemDto;
import kr.megaptera.shoppingMall.dtos.requestUpdateCartItemDto;
import kr.megaptera.shoppingMall.services.CreateCartItemService;
import kr.megaptera.shoppingMall.services.DeleteCartItemService;
import kr.megaptera.shoppingMall.services.GetCartItemService;
import kr.megaptera.shoppingMall.services.GetCartItemsService;
import kr.megaptera.shoppingMall.services.UpdateCartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CreateCartItemService cartService;
    private final GetCartItemsService getCartItemsService;
    private final DeleteCartItemService deleteCartItemService;
    private final UpdateCartItemService updateCartItemService;
    private final GetCartItemService getCartItemService;

    public CartController(
        CreateCartItemService cartService,
        GetCartItemsService getCartItemsService,
        DeleteCartItemService deleteCartItemService,
        UpdateCartItemService updateCartItemService,
        GetCartItemService getCartItemService) {
        this.cartService = cartService;
        this.getCartItemsService = getCartItemsService;
        this.deleteCartItemService = deleteCartItemService;
        this.updateCartItemService = updateCartItemService;
        this.getCartItemService = getCartItemService;
    }

    @PostMapping("products/{product_id}/cartItems")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemDto createCartItem(
        @RequestAttribute("userId") Long userId,
        @PathVariable("product_id") Long productId,
        @RequestBody CreateCartItemDto createCartItemDto
    ) {
        return cartService.create(productId, userId, createCartItemDto);
    }

    @GetMapping
    public CartItemDtos list(
        @RequestAttribute Long userId,
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {

        return getCartItemsService.getCartItems(userId, page);
    }

    @GetMapping("/cartItems/{cartItem_id}")
    public CartItemDetailDto detail(
        @PathVariable("cartItem_id") Long cartItemId
    ) {
        return getCartItemService.getCartItem(cartItemId);
    }

    @DeleteMapping("cartItems")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(
        @RequestBody CartItemIdsDto cartItemIdsDto
    ) {
        deleteCartItemService.deleteCartItem(cartItemIdsDto.getCartItemsId());
    }

    @PatchMapping("cartItems/{cartItem_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(
        @PathVariable("cartItem_id") Long cartItemId,
        @RequestBody requestUpdateCartItemDto requestUpdateCartItemDto
    ) {

        updateCartItemService.updateCartItem(
            cartItemId,
            requestUpdateCartItemDto.getAddAmount(),
            requestUpdateCartItemDto.getDescription(),
            requestUpdateCartItemDto.getQuantity(),
            requestUpdateCartItemDto.getTotalPrice()
        );
    }
}
