package kr.megaptera.shoppingMall.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cart {
  @Id
  @GeneratedValue
  private Long id;

  private Long userId;

  private Long cartItemCount = 0L;

  private Cart() {
  }

  public Cart(Long id, Long userId) {
    this.id = id;
    this.userId = userId;
  }

  public Cart(Long userId) {
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public void addCartItem() {
    this.cartItemCount += 1;
  }

  public void deleteCartItem() {
    this.cartItemCount -= 1;
  }
}
