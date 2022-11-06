package kr.megaptera.shoppingMall.dtos;

public class RecommendationDto {
  private Long id;

  private Long userId;

  private Long reviewId;

  public RecommendationDto(Long id, Long userId, Long reviewId) {
    this.id = id;
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
}
