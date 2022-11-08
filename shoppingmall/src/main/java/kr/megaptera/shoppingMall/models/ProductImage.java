package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ProductImageDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class ProductImage {
  private String url;

  private Boolean isThumbnailImage;

  public ProductImage() {
  }

  public ProductImage(String url, Boolean isThumbnailImage) {
    this.url = url;
    this.isThumbnailImage = isThumbnailImage;
  }

  public String getUrl() {
    return url;
  }

  public Boolean getThumbnailImage() {
    return isThumbnailImage;
  }

  @Override
  public boolean equals(Object other) {
    ProductImage otherProductImage  = (ProductImage) other;

    return this.url.equals(otherProductImage.url) &&
        this.isThumbnailImage.equals(otherProductImage.isThumbnailImage);
  }

  public ProductImageDto toDto() {
    return new ProductImageDto(url, isThumbnailImage);
  }
}
