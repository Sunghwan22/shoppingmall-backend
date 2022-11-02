package kr.megaptera.shoppingMall.dtos;

public class ProductIdDto {
  private Long productId;

  public ProductIdDto() {
  }

  public ProductIdDto(Long productId) {
    this.productId = productId;
  }

  public Long getProductId() {
    return productId;
  }
}
