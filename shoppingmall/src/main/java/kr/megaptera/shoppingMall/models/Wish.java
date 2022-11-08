package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.WishDto;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Wish {
  private Long userId;

  public Wish() {
  }

  public Wish(Long userId) {
    this.userId = userId;

  }
  public Long getUserId() {
    return userId;
  }

  @Override
  public boolean equals(Object other) {
    Wish otherWish = (Wish) other;
    return this.userId.equals(otherWish.userId);
  }
}
