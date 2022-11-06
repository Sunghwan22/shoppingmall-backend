package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class ReviewsDto {
  private List<ReviewDto> reviews;

  private List<ReviewImageDto> reviewImages;

  private List<RecommendationDto> recommendations;

  public ReviewsDto() {
  }

  public ReviewsDto(List<ReviewDto> reviews,
                    List<ReviewImageDto> reviewImages,
                    List<RecommendationDto> recommendations) {
    this.reviews = reviews;
    this.reviewImages = reviewImages;
    this.recommendations = recommendations;
  }

  public List<ReviewDto> getReviews() {
    return reviews;
  }

  public List<ReviewImageDto> getReviewImages() {
    return reviewImages;
  }

  public List<RecommendationDto> getRecommendations() {
    return recommendations;
  }
}
