package kr.megaptera.shoppingMall.dtos;

public class ProductImageDto {
  private Long id;
  private Long productId;
  private String url;
  private Boolean isThumbnailImage;

  public ProductImageDto() {
  }

  public ProductImageDto(Long id, Long productId, String url, Boolean isThumbnailImage) {
    this.id = id;
    this.productId = productId;
    this.url = url;
    this.isThumbnailImage = isThumbnailImage;
  }

  public Long getId() {
    return id;
  }

  public Long getProductId() {
    return productId;
  }

  public String getUrl() {
    return url;
  }

  public Boolean getThumbnailImage() {
    return isThumbnailImage;
  }
}
