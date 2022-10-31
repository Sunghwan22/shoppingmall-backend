package kr.megaptera.shoppingMall.dtos;

public class ProductOptionDto {
  private Long id;

  private Long productId;

  private Long addAmount;

  private String description;

  public ProductOptionDto() {
  }

  public ProductOptionDto(Long id, Long productId, Long addAmount, String description) {
    this.id = id;
    this.productId = productId;
    this.addAmount = addAmount;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public Long getProductId() {
    return productId;
  }

  public Long getAddAmount() {
    return addAmount;
  }

  public String getDescription() {
    return description;
  }
}

