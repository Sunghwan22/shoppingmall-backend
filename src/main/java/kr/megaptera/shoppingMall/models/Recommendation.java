package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.RecommendationDto;

import javax.persistence.Embeddable;

@Embeddable
public class Recommendation {
  private Long userId;

  public Recommendation() {
  }

  public Recommendation( Long userId) {
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }

  public RecommendationDto toDto() {
    return new RecommendationDto(userId);
  }
}
