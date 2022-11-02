package kr.megaptera.shoppingMall.dtos;

public class ImageDto {
  private Long id;

  private String url;

  private Boolean isThumbnailImage;

  private Long productId;

  public ImageDto(Long id, String url, Boolean isThumbnailImage, Long productId) {
    this.id = id;
    this.url = url;
    this.isThumbnailImage = isThumbnailImage;
    this.productId = productId;
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
}
