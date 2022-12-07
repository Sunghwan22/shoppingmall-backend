package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.WishDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wishes")
public class Wish {
  @Id
  @GeneratedValue
  private Long id;

  private Long productId;

  private Long userId;

  private Wish() {
  }

  public Wish(Long id, Long productId, Long userId) {
    this.id = id;
    this.productId = productId;
    this.userId = userId;
  }

  public Wish(Long productId, Long userId) {
    this.productId = productId;
    this.userId = userId;
  }

  public Long userId() {
    return userId;
  }

  public WishDto toDto() {
    return new WishDto(id, productId, userId);
  }

  public Long productId() {
    return productId;
  }
}
