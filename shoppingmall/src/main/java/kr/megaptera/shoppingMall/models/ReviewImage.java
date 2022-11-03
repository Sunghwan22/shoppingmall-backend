package kr.megaptera.shoppingMall.models;

import jdk.jfr.Enabled;
import kr.megaptera.shoppingMall.dtos.ReviewImageDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReviewImage {
  @Id
  @GeneratedValue
  private Long id;

  private String url;

  private Long reviewId;

  private Long productId;

  public ReviewImage() {
  }

  public ReviewImage(Long id, String url, Long reviewId, Long productId) {
    this.id = id;
    this.url = url;
    this.reviewId = reviewId;
    this.productId = productId;
  }

  public Long getId() {
    return id;
  }

  public Long getReviewId() {
    return reviewId;
  }

  public String getUrl() {
    return url;
  }

  public Long getProductId() {
    return productId;
  }

  public ReviewImageDto toDto() {
    return new ReviewImageDto();
  }
}
