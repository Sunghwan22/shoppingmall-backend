package kr.megaptera.shoppingMall.dtos;

public class WishDto {
  private Long id;
  private Long productId;
  private Long userId;

  public WishDto(Long id, Long productId, Long userId) {
    this.id = id;
    this.productId = productId;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public Long getProductId() {
    return productId;
  }

  public Long getUserId() {
    return userId;
  }
}
