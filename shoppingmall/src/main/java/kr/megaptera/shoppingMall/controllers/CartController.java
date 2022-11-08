package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.services.CartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }
//  @GetMapping
//  public List<CartItemDto> list(
//      @RequestAttribute Long userId
//  ) {
//    List<CartItem> cartItems = cartService.list(userId);
//
//    List<Image> thumbNailImages = imageService.findThumbNailImages(cartItems);
//   이미지도 찾아야 되 그 리스트도 해야 되
//    return cartItemDtos;
//  }
//
//  @PostMapping("product/{id}")
//  @ResponseStatus(HttpStatus.CREATED)
//  public CartItemDto createCartItem(
//      @PathVariable("id") Long productId,
//      @RequestAttribute Long userId,
//      @RequestBody CreateCartItemDto createCartItemDto
//  ) {
//    CartItem cartItem = cartService.createCartItem(productId, userId, createCartItemDto);
//
//    return cartItem.toDto(thumbNailImageUrl);
//  }
}
