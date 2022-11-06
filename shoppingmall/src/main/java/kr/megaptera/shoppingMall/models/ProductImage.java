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

  private String url;

  private Boolean isThumbnailImage;

  private Long productId;

  public ProductImage() {
  }

  public ProductImage(Long id, Long productId, String url, Boolean isThumbnailImage) {
    this.id = id;
    this.productId = productId;
    this.url = url;
    this.isThumbnailImage = isThumbnailImage;
  }

  public Long getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public Boolean getThumbnailImage() {
    return isThumbnailImage;
  }

  public Long getProductId() {
    return productId;
  }

  public ProductImageDto toDto() {
    return new ProductImageDto(id, url, isThumbnailImage, productId);
  }
}
