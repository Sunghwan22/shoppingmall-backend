package kr.megaptera.shoppingMall.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Wish {
  @Id
  @GeneratedValue
  @Column(name = "wish_id")
  private Long id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "product_id")
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

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public Product getProduct() {
    return product;
  }
}
