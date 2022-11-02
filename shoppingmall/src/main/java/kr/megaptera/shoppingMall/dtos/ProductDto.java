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

  private List<ImageDto> imageDtos;

  private List<OptionDto> optionDtos;

  private List<WishDto> wishDtos;

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
                    List<ImageDto> imageDtos,
                    List<OptionDto> optionDtos,
                    List<WishDto> wishDtos) {
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
    this.imageDtos = imageDtos;
    this.optionDtos = optionDtos;
    this.wishDtos = wishDtos;
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

  public List<ImageDto> getImageDtos() {
    return imageDtos;
  }

  public List<OptionDto> getOptionDtos() {
    return optionDtos;
  }

  public List<WishDto> getWishDtos() {
    return wishDtos;
  }

  public int wishes() {
    return wishDtos.size();
  }
}
