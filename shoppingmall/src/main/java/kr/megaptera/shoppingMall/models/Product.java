package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.dtos.ProductOptionDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Product {
  @Id
  @GeneratedValue
  private Long id;

  @GeneratedValue
  private Long productNumber;

  private String productName;

  private String maker;

  private String category;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  private Long views;

  private Long cumulativeSales;

  private Long likes;

  private Long price;

  private Long wish;

  private Long stock;

  private Long maximumQuantity;

  private String description;

  public Product() {
  }

  public Product(long id,
                 long productNumber,
                 String productName,
                 String maker,
                 String category,
                 long views,
                 long cumulativeSales,
                 long likes,
                 long price,
                 long wish,
                 long stock,
                 long maximumQuantity,
                 String description) {

    this.id = id;
    this.productNumber = productNumber;
    this.productName = productName;
    this.maker = maker;
    this.category = category;
    this.views = views;
    this.cumulativeSales = cumulativeSales;
    this.likes = likes;
    this.price = price;
    this.wish = wish;
    this.stock = stock;
    this.maximumQuantity = maximumQuantity;
    this.description = description;
  }

  public ProductDto toDto(List<ProductImageDto> productImages, List<ProductOptionDto> productOptions) {
    return new ProductDto(id, productNumber, productName, maker, category,
        createdAt, updatedAt, views,
        cumulativeSales, likes, price,
        wish, stock, maximumQuantity, description, productImages, productOptions);
  }

  public String maker() {
    return maker;
  }
}
