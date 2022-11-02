package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ImageDto;
import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.WishDto;
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

  private Long views;

  private Long cumulativeSales;

  private Long price;

  private Long stock;

  private Long maximumQuantity;

  private String description;

  private Long deliveryFee;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public Product() {
  }

  public Product(Long id,
                 Long productNumber,
                 String productName,
                 String maker,
                 String category,
                 Long views,
                 Long cumulativeSales,
                 Long price,
                 Long stock,
                 Long maximumQuantity,
                 String description) {

    this.id = id;
    this.productNumber = productNumber;
    this.productName = productName;
    this.maker = maker;
    this.category = category;
    this.views = views;
    this.cumulativeSales = cumulativeSales;
    this.price = price;
    this.stock = stock;
    this.maximumQuantity = maximumQuantity;
    this.description = description;
  }

  public String maker() {
    return maker;
  }

  public ProductDto toDto(List<ImageDto> imageDtos, List<OptionDto> optionDtos, List<WishDto> wishDtos) {
    return new ProductDto(
        id, productNumber,productName,
        maker, category,
        views, cumulativeSales,
        price, stock,
        maximumQuantity,description, deliveryFee,
        imageDtos, optionDtos, wishDtos);
  }

  public Long id() {
    return id;
  }

  public CartItem toCartItem(Long quantity, Option option) {
    return new CartItem(id, productName, maker, category, price, stock, description, deliveryFee);
  }
}
