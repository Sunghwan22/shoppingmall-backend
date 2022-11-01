package kr.megaptera.shoppingMall.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.name;

@Entity
public class Image {
  @Id
  @GeneratedValue
  private Long id;

  private String url;

  private Boolean isThumbnailImage;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private Product product;

  public Image() {
  }

  public Image(Long id, Product product, String url, Boolean isThumbnailImage) {
    this.id = id;
    this.product = product;
    this.url = url;
    this.isThumbnailImage = isThumbnailImage;
  }

  public ProductImageDto toDto() {
    return new ProductImageDto(id, url, isThumbnailImage);
  }

  public Long id() {
    return id;
  }

  public String url() {
    return url;
  }

  public Boolean isThumbnailImage() {
    return isThumbnailImage;
  }
}
