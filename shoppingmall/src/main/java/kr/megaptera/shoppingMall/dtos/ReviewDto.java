package kr.megaptera.shoppingMall.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReviewDto {
  private Long id;

  private Long productId;

  private Long rating;

  private Long userId;

  private String optionName;

  private String content;

  private Boolean isBestReview;

  private String createdAt;

  private String userNickName;

  private List<ReviewDto> reviews;

  private List<ReviewImageDto> reviewImages;

  private List<RecommendationDto> recommendations;

  public ReviewDto() {
  }

  public ReviewDto(Long id, Long productId,
                   Long rating, Long userId,
                   String optionName, String content,
                   Boolean isBestReview, String userNickName) {
    this.id = id;
    this.productId = productId;
    this.rating = rating;
    this.userId = userId;
    this.optionName = optionName;
    this.content = content;
    this.isBestReview = isBestReview;
    this.userNickName = userNickName;
  }

  public ReviewDto(List<ReviewDto> reviews,
                   List<ReviewImageDto> reviewImages,
                   List<RecommendationDto> recommendations) {
    this.reviews = reviews;
    this.reviewImages = reviewImages;
    this.recommendations = recommendations;
  }

  public Long getId() {
    return id;
  }

  public Long getProductId() {
    return productId;
  }

  public Long getRating() {
    return rating;
  }

  public Long getUserId() {
    return userId;
  }

  public String getOptionName() {
    return optionName;
  }

  public String getContent() {
    return content;
  }

  public Boolean getBestReview() {
    return isBestReview;
  }

  public String getUserNickName() {
    return userNickName;
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

  public String getCreatedAt() {
    LocalDate now = LocalDate.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    return now.format(formatter);
  }
}
