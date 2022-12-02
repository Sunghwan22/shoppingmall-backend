package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.CartItemDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CartItem {
    @Id
    @GeneratedValue
    private Long id;

    private Long quantity;

    private Long addAmount;

    private String description;

    private Long totalPrice;

    private Long deliveryFee;

    private String name;

    private Long cartId;

    private Long productId;

    private ProductImage cartItemImage;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private CartItem() {
    }

    public CartItem(Long quantity,
                    Long addAmount,
                    String description,
                    Long totalPrice,
                    Long deliveryFee,
                    String name,
                    Long cartId,
                    Long productId,
                    ProductImage cartItemImage) {

        this.quantity = quantity;
        this.addAmount = addAmount;
        this.description = description;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
        this.name = name;
        this.cartId = cartId;
        this.productId = productId;
        this.cartItemImage = cartItemImage;
    }

    public CartItem(
        Long id,
        Long quantity,
        Long addAmount,
        String description,
        Long totalPrice,
        Long deliveryFee,
        String name,
        Long cartId,
        Long productId,
        ProductImage cartItemImage) {

        this.id = id;
        this.quantity = quantity;
        this.addAmount = addAmount;
        this.description = description;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
        this.name = name;
        this.cartId = cartId;
        this.productId = productId;
        this.cartItemImage = cartItemImage;
    }

    public CartItemDto toDto() {
        return new CartItemDto(
            id, quantity, addAmount, description, totalPrice, deliveryFee, name
            , cartId, productId, cartItemImage.toDto()
        );
    }

    public static CartItem fake() {
        return new CartItem(
            1L,
            1L,
            33000L,
            "옵션명",
            3000L,
            3000L,
            "아이폰14",
            1L,
            1L,
            new ProductImage("image", true)
        );
    }

    public void addAmount(Long addAmount) {
        this.addAmount = addAmount;
    }

    public void quantity(Long quantity) {
        this.quantity = quantity;
    }

    public void description(String description) {
        this.description = description;
    }

    public void totalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
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

    public Long getTotalPrice() {
        return totalPrice;
    }

    public Long productId() {
        return productId;
    }
}
