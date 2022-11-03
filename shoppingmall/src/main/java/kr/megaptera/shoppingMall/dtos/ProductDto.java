package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class ProductDto {
  private Long id;

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

  private List<ImageDto> images;

  private List<OptionDto> options;

  private List<WishDto> wishUserList;

  private List<ReviewDto> reviews;
  private List<ReviewImageDto> reviewImages;

  public ProductDto() {
  }

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
                    Long deliveryFee,
                    List<ImageDto> imageDtos,
                    List<OptionDto> optionDtos,
                    List<WishDto> wishDtos,
                    List<ReviewDto> reviews,
                    List<ReviewImageDto> reviewImages) {
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
    this.deliveryFee = deliveryFee;
    this.images = imageDtos;
    this.options = optionDtos;
    this.wishUserList = wishDtos;
    this.reviews = reviews;
    this.reviewImages = reviewImages;
  }

  public Long getId() {
    return id;
  }

  public Long getProductNumber() {
    return productNumber;
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

  public Long getDeliveryFee() {
    return deliveryFee;
  }

  public List<ImageDto> getImages() {
    return images;
  }

  public List<OptionDto> getOptions() {
    return options;
  }

  public List<WishDto> getWishUserList() {
    return wishUserList;
  }

  public List<ReviewDto> getReviews() {
    return reviews;
  }

  public List<ReviewImageDto> getReviewImages() {
    return reviewImages;
  }

  public int getWishes() {
    return wishUserList.size();
  }
}
