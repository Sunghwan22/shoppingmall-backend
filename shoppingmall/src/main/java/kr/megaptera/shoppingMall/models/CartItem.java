package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.CartItemDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CartItem {
  @Id
  @GeneratedValue
  private Long id;
  // cartRepositor에서 findByUserId로 카트를 찾은 다음에 cartItem을 create할 때 cartId도 같이 넘겨줘서
  // cart.cartItemId 를 통해서 카트 아이템을 찾는다?
  private Long cartId;

  private Long productId;

  public CartItem(Long id, Long cartId, Long productId) {
    this.id = id;
    this.cartId = cartId;
    this.productId = productId;
  }

  public Long getId() {
    return id;
  }

  public Long getCartId() {
    return cartId;
  }

  public Long getProductId() {
    return productId;
  }

  public CartItemDto toDto() {
    return null;
  }
}
