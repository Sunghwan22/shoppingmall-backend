package kr.megaptera.shoppingMall.models;

import jdk.jfr.Enabled;
import kr.megaptera.shoppingMall.dtos.ReviewImageDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class ReviewImage {
  private String url;

  private Long reviewId;

  public ReviewImage() {
  }

  public ReviewImage(String url, Long reviewId) {
    this.url = url;
    this.reviewId = reviewId;
  }

  public Long getReviewId() {
    return reviewId;
  }

  public String getUrl() {
    return url;
  }
}
