package kr.megaptera.shoppingMall.dtos;

public class ProductImageDto {
  private String url;

  private Boolean isThumbnailImage;

  public ProductImageDto(String url, Boolean isThumbnailImage) {
    this.url = url;
    this.isThumbnailImage = isThumbnailImage;
  }

  public String getUrl() {
    return url;
  }

  public Boolean getThumbnailImage() {
    return isThumbnailImage;
  }
}
