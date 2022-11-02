package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.CartItemDto;
import kr.megaptera.shoppingMall.dtos.CreateCartItemDto;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.services.CartService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @PostMapping("product/{id}")
  public CartItemDto createCartItem(
      @PathVariable Long productId,
      @RequestAttribute Long userId,
      @RequestBody CreateCartItemDto createCartItemDto
  ) {

    CartItem cartItem = cartService.createCartItem(productId, userId, createCartItemDto);

    return cartItem.toDto();
  }


}
