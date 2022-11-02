package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.OptionDto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Option {
  @Id
  @GeneratedValue
  private Long id;

  private Long addAmount;

  private String description;

  private Long productId;

  public Option() {
  }

  public Option(Long id, Long productId, Long addAmount, String description) {
    this.id = id;
    this.productId = productId;
    this.addAmount = addAmount;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public Long getAddAmount() {
    return addAmount;
  }

  public String getDescription() {
    return description;
  }

  public Long productId() {
    return productId;
  }

  public OptionDto toDto() {
    return new OptionDto(id, productId, addAmount, description);
  }
}
