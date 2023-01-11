package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ReviewImageDto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReviewImage {
  @Column(length = 9999)
  private String url;

  public ReviewImage() {
  }

  public ReviewImage(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public ReviewImageDto toDto() {
    return new ReviewImageDto(url);
  }
}
