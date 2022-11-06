package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.RecommendationDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Recommendation {
  @Id
  @GeneratedValue
  private Long id;

  private Long userId;

  private Long reviewId;

  public Recommendation() {
  }

  public Recommendation(Long id, Long userId, Long reviewId) {
    this.id = id;
    this.userId = userId;
    this.reviewId = reviewId;
  }

  public Recommendation(Long userId, Long reviewId) {
    this.userId = userId;
    this.reviewId = reviewId;
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getReviewId() {
    return reviewId;
  }

  public RecommendationDto toDto() {
    return new RecommendationDto(id, userId, reviewId);
  }
}
