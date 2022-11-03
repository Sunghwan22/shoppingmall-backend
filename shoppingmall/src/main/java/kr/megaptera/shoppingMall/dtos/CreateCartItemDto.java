package kr.megaptera.shoppingMall.dtos;

import kr.megaptera.shoppingMall.models.Option;

public class CreateCartItemDto {
  private Long quantity;

  private Option option;

  public CreateCartItemDto() {
  }

  public CreateCartItemDto(Long quantity, Option option) {
    this.quantity = quantity;
    this.option = option;
  }

  public Long getQuantity() {
    return quantity;
  }

  public Option getOption() {
    return option;
  }
}
