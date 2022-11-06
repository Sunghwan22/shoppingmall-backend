package kr.megaptera.shoppingMall.dtos;

public class ReviewImageDto {
  private Long id;

  private String url;

  private Long reviewId;

  private Long productId;

  public ReviewImageDto() {
  }

  public ReviewImageDto(Long id, Long reviewId, Long productId, String url) {
    this.id = id;
    this.reviewId = reviewId;
    this.productId = productId;
    this.url = url;
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

  public Long getId() {
    return id;
  }
}
