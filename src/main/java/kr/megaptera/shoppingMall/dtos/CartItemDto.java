package kr.megaptera.shoppingMall.dtos;

public class CartItemDto {
  private Long productId;

  private String productName;

  private String maker;

  private String category;

  private Long cartItemPrice;

  private Long stock;

  private String description;

  private Long deliveryFee;

  private Long quantity;

  private String optionName;

  private ProductImageDto thumbNailImage;

  public CartItemDto() {
  }

  public CartItemDto(Long productId,
                     String productName,
                     String maker,
                     String category,
                     Long cartItemPrice,
                     Long stock,
                     String description,
                     Long deliveryFee,
                     Long quantity,
                     String optionName,
                     ProductImageDto thumbNailImage) {
    this.productId = productId;
    this.productName = productName;
    this.maker = maker;
    this.category = category;
    this.cartItemPrice = cartItemPrice;
    this.stock = stock;
    this.description = description;
    this.deliveryFee = deliveryFee;
    this.quantity = quantity;
    this.optionName = optionName;
    this.thumbNailImage = thumbNailImage;
  }

  public Long getProductId() {
    return productId;
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

  public Long getCartItemPrice() {
    return cartItemPrice;
  }

  public Long getStock() {
    return stock;
  }

  public String getDescription() {
    return description;
  }

  public Long getDeliveryFee() {
    return deliveryFee;
  }

  public Long getQuantity() {
    return quantity;
  }

  public String getOptionName() {
    return optionName;
  }

  public ProductImageDto getThumbNailImage() {
    return thumbNailImage;
  }
}
