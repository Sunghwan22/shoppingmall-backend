package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.CartItemDto;
import kr.megaptera.shoppingMall.dtos.CreateCartItemDto;
import kr.megaptera.shoppingMall.services.CreateCartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {
  private final CreateCartItemService cartService;

  public CartController(CreateCartItemService cartService) {
    this.cartService = cartService;
  }

  @PostMapping("cartItems/products/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public CartItemDto createCartItem(
      @PathVariable("id") Long productId,  //
      @RequestAttribute Long userId,
      @RequestBody CreateCartItemDto createCartItemDto
  ) {
    return cartService.create(productId, userId, createCartItemDto);
  }

//  @GetMapping
//  public List<CartItemDto> list(
//      @RequestAttribute Long userId
//  ) {
//    List<CartItem> cartItems = cartService.list(userId);
//
//    List<Image> thumbNailImages = imageService.findThumbNailImages(cartItems);
//    return cartItemDtos;
//  }
}
