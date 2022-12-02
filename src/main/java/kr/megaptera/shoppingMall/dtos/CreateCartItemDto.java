package kr.megaptera.shoppingMall.dtos;

import kr.megaptera.shoppingMall.models.Option;

public class CreateCartItemDto {
  private Long quantity;

  private Option option;

  private Long totalPayment;

  public CreateCartItemDto() {
  }

  public CreateCartItemDto(Long quantity, Option option, Long totalPayment) {
    this.quantity = quantity;
    this.option = option;
    this.totalPayment = totalPayment;
  }

  public Long getQuantity() {
    return quantity;
  }

  public Option getOption() {
    return option;
  }

  public Long getTotalPayment() {
    return totalPayment;
  }
}
