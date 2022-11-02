package kr.megaptera.shoppingMall.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.megaptera.shoppingMall.dtos.ProductOptionDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Option {
  @Id
  @GeneratedValue
  @Column(name = "option_id")
  private Long id;

  private Long addAmount;

  private String description;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public Option() {
  }

  public Option(Long id, Product product, Long addAmount, String description) {
    this.id = id;
    this.product = product;
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

  public Product getProduct() {
    return product;
  }

  public ProductOptionDto toDto() {
    return new ProductOptionDto(id, addAmount , description);
  }
}
