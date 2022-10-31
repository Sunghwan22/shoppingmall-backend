package kr.megaptera.shoppingMall.models;

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
}
