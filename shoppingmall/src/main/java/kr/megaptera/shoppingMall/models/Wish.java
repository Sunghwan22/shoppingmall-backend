package kr.megaptera.shoppingMall.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Wish {
  @Id
  @GeneratedValue
  private Long id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "PERSON_ID")
  private User user;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private Product product;

  public Wish() {
  }

  public Wish(Long id, User user, Product product) {
    this.id = id;
    this.user = user;
    this.product = product;
  }

  public Wish(Product product, User user) {
    this.product = product;
    this.user = user;
  }

  public Long id() {
    return id;
  }

  public User user() {
    return user;
  }

  public Product product() {
    return product;
  }
}
