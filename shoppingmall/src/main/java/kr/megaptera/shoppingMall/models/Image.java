package kr.megaptera.shoppingMall.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Image {
  @Id
  @GeneratedValue
  @Column(name = "image_id")
  private Long id;

  private String url;

  private Boolean isThumbnailImage;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public Image() {
  }

  public Image(Long id, Product product, String url, Boolean isThumbnailImage) {
    this.id = id;
    this.product = product;
    this.url = url;
    this.isThumbnailImage = isThumbnailImage;
  }

  public Long getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public Boolean getThumbnailImage() {
    return isThumbnailImage;
  }

  public Product getProduct() {
    return product;
  }
}
