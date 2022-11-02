package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ImageDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image {
  @Id
  @GeneratedValue
  private Long id;

  private String url;

  private Boolean isThumbnailImage;

  private Long productId;

  public Image() {
  }

  public Image(Long id, Long productId, String url, Boolean isThumbnailImage) {
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

  public ImageDto toDto() {
    return new ImageDto(id, url, isThumbnailImage, productId);
  }
}
