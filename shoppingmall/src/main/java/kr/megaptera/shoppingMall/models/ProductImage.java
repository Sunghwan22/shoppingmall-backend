package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ProductImageDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductImage {
  @Id
  @GeneratedValue
  private Long id;

  private Long productId;

  private String url;

  private Boolean isThumbnailImage;

  public ProductImage(Long id, Long productId, String url, Boolean isThumbnailImage) {
    this.id = id;
    this.productId = productId;
    this.url = url;
    this.isThumbnailImage = isThumbnailImage;
  }

  public ProductImageDto toDto() {
    return new ProductImageDto(id,productId,url,isThumbnailImage);
  }

  public Long id() {
    return id;
  }

  public Long productId() {
    return productId;
  }

  public String url() {
    return url;
  }

  public Boolean isThumbnailImage() {
    return isThumbnailImage;
  }
}
