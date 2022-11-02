package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.WishDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wish {
  @Id
  @GeneratedValue
  private Long id;

  private Long userId;

  private Long productId;

  public Wish() {
  }

  public Wish(Long id, Long userId, Long productId) {
    this.id = id;
    this.userId = userId;
    this.productId = productId;
  }

  public Wish(Long productId, Long userId) {
    this.productId = productId;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getProductId() {
    return productId;
  }

  public WishDto toDto() {
    return new WishDto(id, productId, userId);
  }
}
