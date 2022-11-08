package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.OptionDto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Option {
  private Long addAmount;

  private String description;

  public Option() {
  }

  public Option(Long addAmount, String description) {
    this.addAmount = addAmount;
    this.description = description;
  }

  public Long getAddAmount() {
    return addAmount;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object other) {
    Option otherOption = (Option) other;

    return this.addAmount.equals(otherOption.addAmount) &&
        this.description.equals(((Option) other).description);
  }

  public OptionDto toDto() {
    return new OptionDto(addAmount, description);
  }
}
