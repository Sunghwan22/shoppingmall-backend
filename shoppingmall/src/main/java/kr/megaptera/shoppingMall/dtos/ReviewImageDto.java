package kr.megaptera.shoppingMall.dtos;

public class ReviewImageDto {
  private Long reviewImageId;

  private String url;

  private Long reviewId;

  private Long productId;

  public ReviewImageDto() {
  }

  public ReviewImageDto(Long ReviewImageId, String url, Long reviewId, Long productId) {
    this.reviewImageId = ReviewImageId;
    this.url = url;
    this.reviewId = reviewId;
    this.productId = productId;
  }

  public Long getReviewImageId() {
    return reviewImageId;
  }

  public String getUrl() {
    return url;
  }

  public Long getReviewId() {
    return reviewId;
  }

  public Long getProductId() {
    return productId;
  }
}
