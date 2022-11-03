package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.CartItemDto;
import kr.megaptera.shoppingMall.dtos.CreateCartItemDto;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.services.CartService;
import kr.megaptera.shoppingMall.services.ImageService;
import kr.megaptera.shoppingMall.services.WishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
  private final CartService cartService;
  private final WishService wishService;
  private final ImageService imageService;

  public CartController(CartService cartService, WishService wishService, ImageService imageService) {
    this.cartService = cartService;
    this.wishService = wishService;
    this.imageService = imageService;
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

  @PostMapping("product/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public CartItemDto createCartItem(
      @PathVariable("id") Long productId,
      @RequestAttribute Long userId,
      @RequestBody CreateCartItemDto createCartItemDto
  ) {
    CartItem cartItem = cartService.createCartItem(productId, userId, createCartItemDto);

    String thumbNailImageUrl = imageService.findThumbNailImage(productId);

    return cartItem.toDto(thumbNailImageUrl); // 이렇게 되면은 리스트로 CartItem을 주면 어떻게 되지?
  }
}
