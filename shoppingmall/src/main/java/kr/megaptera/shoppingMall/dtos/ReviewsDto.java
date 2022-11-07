package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class ReviewsDto {
  private List<ReviewDto> reviews;

  private List<ReviewImageDto> reviewImages;

  private List<RecommendationDto> recommendations;

  private int pageNumber;

  private int totalReviewsNumber;

  private double totalRating;

  private List<ReviewDto> bestReviews;

  private List<RecommendationDto> bestReviewRecommendations;

  public ReviewsDto() {
  }

  public ReviewsDto(List<ReviewDto> reviews,
                    List<ReviewImageDto> reviewImages,
                    List<RecommendationDto> recommendations,
                    int pageNumber,
                    int totalReviewsNumber,
                    double totalRating) {
    this.reviews = reviews;
    this.reviewImages = reviewImages;
    this.recommendations = recommendations;
    this.pageNumber = pageNumber;
    this.totalReviewsNumber = totalReviewsNumber;
    this.totalRating = totalRating;
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

  public int getPageNumber() {
    return pageNumber;
  }

  public int getTotalReviewsNumber() {
    return totalReviewsNumber;
  }

  public double getTotalRating() {
    return totalRating;
  }
}
