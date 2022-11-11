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
      @PathVariable("id") Long productId,
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
//
//    // 모델일까 도메인 모델일까? 근데 프론트에서 하면 저장을 못하는데 ? 로컬 스토리지에 저장을 해도 될 것 같긴하내
//    // 근데 서버에 저장하는 게 맞는 것 같다. create를 어떻게 해야할까?
//    // cartItem과 product의 차이는 선택한 옵션과 총 금액을 가지고 있음.
//    // 상세페이지는 어차피 product의 상세 페이지이고 , 아니면 장바구니 안에 있는 아이템을 값 객체로 사용을 할 것인가?
//    // 모든 정보가 동일할떄 장바구니에 있는 상품은 같아도 상관이 없는가? 아니면 달라야 하나? 중복을 허용할 것인가? 중복을 허용하지 않을 것인가?
//    // 장바구니에 있는 상품으로 고객은 무엇을 할 수 있는가?
//    // 여러번 결제를 피하고 한 번에 결제하고 싶다.
//    // 장바구니에는 상품, 옵션 , 수량이 같아도 별개의 것으로 취급한다 => 도메인 모델 식별자를 가진다.
//    // 장바구니에 들어있는 상품의 갯수를 확인할 수 있다.
//    // 장바구니가 예를 들고 있는 상태인데 이 친구도 entity이다.
//    return cartItemDtos;
//  }
}
