package kr.megaptera.shoppingMall.dtos;

public class CartItemDto {
  private Long id;

  private Long quantity;

  private Long addAmount;

  private String description;

  private Long totalPayment;

  private Long deliveryFee;

  private String name;

  private Long cartId;

  private Long productId;

  private String image;

  public CartItemDto() {
  }

  public CartItemDto(Long id,
                     Long quantity,
                     Long addAmount,
                     String description,
                     Long totalPayment,
                     Long deliveryFee,
                     String name,
                     Long cartId,
                     Long productId,
                     String image) {
    this.id = id;
    this.quantity = quantity;
    this.addAmount = addAmount;
    this.description = description;
    this.totalPayment = totalPayment;
    this.deliveryFee = deliveryFee;
    this.name = name;
    this.cartId = cartId;
    this.productId = productId;
    this.image = image;
  }

  public Long getId() {
    return id;
  }

  public Long getQuantity() {
    return quantity;
  }

  public Long getAddAmount() {
    return addAmount;
  }

  public String getDescription() {
    return description;
  }

  public Long getTotalPayment() {
    return totalPayment;
  }

  public Long getDeliveryFee() {
    return deliveryFee;
  }

  public String getName() {
    return name;
  }

  public Long getCartId() {
    return cartId;
  }

  public Long getProductId() {
    return productId;
  }

  public String getImage() {
    return image;
  }
}
