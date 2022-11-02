package kr.megaptera.shoppingMall.dtos;

import kr.megaptera.shoppingMall.models.Image;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Wish;

import java.util.List;

public class ProductDto {
  private Long id;

  private String productName;

  private String maker;

  private String category;

  private Long views;

  private Long cumulativeSales;

  private Long price;

  private Long stock;

  private Long maximumQuantity;

  private String description;

  private List<Image> images;

  private List<Option> options;

  private List<Wish> wishUserList;

  private Long productNumber;

  public ProductDto(Long id,
                    Long productNumber,
                    String productName,
                    String maker,
                    String category,
                    Long views,
                    Long cumulativeSales,
                    Long price,
                    Long stock,
                    Long maximumQuantity,
                    String description,
                    List<Image> images,
                    List<Option> options,
                    List<Wish> wishUserList) {
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
    this.images = images;
    this.options = options;
    this.wishUserList = wishUserList;
  }

  public ProductDto() {
  }

  public Long getId() {
    return id;
  }

  public String getProductName() {
    return productName;
  }

  public String getMaker() {
    return maker;
  }

  public String getCategory() {
    return category;
  }

  public Long getViews() {
    return views;
  }

  public Long getCumulativeSales() {
    return cumulativeSales;
  }

  public Long getPrice() {
    return price;
  }

  public Long getStock() {
    return stock;
  }

  public Long getMaximumQuantity() {
    return maximumQuantity;
  }

  public String getDescription() {
    return description;
  }

  public List<Image> getImages() {
    return images;
  }

  public List<Option> getOptions() {
    return options;
  }

  public List<Wish> getWishUserList() {
    return wishUserList;
  }

  public Long getProductNumber() {
    return productNumber;
  }

  public int getWishes() {
    return wishUserList.size();
  }
}
