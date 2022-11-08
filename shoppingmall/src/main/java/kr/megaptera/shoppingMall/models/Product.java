package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.WishDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

  @ElementCollection
  List<ProductImage> productImages = new ArrayList<>();

  @ElementCollection
  List<Option> options = new ArrayList<>();

  @ElementCollection
  List<Wish> wishes = new ArrayList<>();

  public Product() {
  }

  public Product(Long id,
                 Long productNumber,
                 List<ProductImage> productImages,
                 List<Option> options,
                 List<Wish> wishes,
                 String productName,
                 String maker,
                 String category,
                 Long views,
                 Long cumulativeSales,
                 Long price,
                 Long stock,
                 Long maximumQuantity,
                 String description,
                 Long deliveryFee) {
    this.id = id;
    this.productNumber = productNumber;
    this.productImages = productImages;
    this.options = options;
    this.wishes = wishes;
    this.productName = productName;
    this.maker = maker;
    this.category = category;
    this.views = views;
    this.cumulativeSales = cumulativeSales;
    this.price = price;
    this.stock = stock;
    this.maximumQuantity = maximumQuantity;
    this.description = description;
    this.deliveryFee = deliveryFee;
  }

  public Long id() {
    return id;
  }

  public ProductDto toDto() {
    return new ProductDto(
        id, productNumber, productName,
        maker, category,
        views, cumulativeSales,
        price, stock,
        maximumQuantity, description, deliveryFee,
        productImages, options, wishes, createdAt, updatedAt
        );
  }

  public CartItem toCartItem(Long quantity, Long addAmount, String optionName, Long cartId) {
    Long cartItemPrice = (price + addAmount) * quantity;

    return new CartItem(id, productName,
        maker, category, cartItemPrice,
        stock, description, deliveryFee,
        quantity, optionName, cartId);
  }

  public List<Wish> wishes() {
    return wishes;
  }

  public String maker() {
    return maker;
  }

  public int WishesNumber() {
    return wishes.size();
  }

  public List<ProductImage> getProductImages() {
    return productImages;
  }

  public List<Option> getOptions() {
    return options;
  }

  public List<Wish> getWishes() {
    return wishes;
  }
}
