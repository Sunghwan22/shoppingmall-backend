package kr.megaptera.shoppingMall.dtos;

import kr.megaptera.shoppingMall.models.Product;

public class OptionDto {
  private Long addAmount;

  private String description;

  public OptionDto() {
  }

  public OptionDto(Long addAmount, String description) {
    this.addAmount = addAmount;
    this.description = description;
  }

  public Long getAddAmount() {
    return addAmount;
  }

  public String getDescription() {
    return description;
  }
}

