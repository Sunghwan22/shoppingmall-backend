package kr.megaptera.shoppingMall.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductImage {
  @Id
  @GeneratedValue
  private Long id;

  private Long productId;

  private String url;

  private Boolean isThumbnailImage;
}
