package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.CartItemDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CartItem {
  @Id
  @GeneratedValue
  private Long id;
  // cartRepositor에서 findByUserId로 카트를 찾은 다음에 cartItem을 create할 때 cartId도 같이 넘겨줘서
  // cart.cartItemId 를 통해서 카트 아이템을 찾는다?
  private Long cartId;

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

  public CartItem() {
  }

  public CartItem(Long productId,
                  String productName,
                  String maker,
                  String category,
                  Long cartItemPrice,
                  Long stock,
                  String description,
                  Long deliveryFee,
                  Long quantity,
                  String optionName,
                  Long cartId) {
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
    this.cartId = cartId;
  }

  public CartItem(Long id,
                  Long productId,
                  String productName,
                  String maker,
                  String category,
                  Long cartItemPrice,
                  Long stock,
                  String description,
                  Long deliveryFee,
                  Long quantity,
                  String optionName,
                  Long cartId) {
    this.id = id;
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
    this.cartId = cartId;
  }

  public Long getId() {
    return id;
  }

  public Long getCartId() {
    return cartId;
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

  public CartItemDto toDto(String thumbNailImage) {
    return new CartItemDto(productId, productName, maker, category, cartItemPrice, stock, description
        , deliveryFee, quantity, optionName, thumbNailImage);
  }
}
