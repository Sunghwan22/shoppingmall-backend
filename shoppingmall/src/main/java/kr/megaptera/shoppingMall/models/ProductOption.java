package kr.megaptera.shoppingMall.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ProductOption {
  @Id
  @GeneratedValue
  private Long id;

  private Long productId;

  private Long addAmount;

  private String description;

  public ProductOption() {
  }

  public ProductOption(Long id, Long productId, Long addAmount, String description) {
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
