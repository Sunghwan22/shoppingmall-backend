package kr.megaptera.shoppingMall.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderDto {
    private Long id;

    private Long userId;

    private Long productId;

    private String name;

    private String phoneNumber;

    private String imageUrl;

    private AddressDto address;

    private Long quantity;

    private Long orderPayment;

    private String deliveryRequest;

    private String description;

    private OrderDto() {
    }

    public OrderDto(Long id,
                    Long productId,
                    Long userId,
                    String name,
                    String phoneNumber,
                    String imageUrl,
                    AddressDto address,
                    Long quantity,
                    Long orderPayment,
                    String deliveryRequest,
                    String description) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.address = address;
        this.quantity = quantity;
        this.orderPayment = orderPayment;
        this.deliveryRequest = deliveryRequest;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddressDto getAddress() {
        return address;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getOrderPayment() {
        return orderPayment;
    }

    public String getDeliveryRequest() {
        return deliveryRequest;
    }

    public String getDescription() {
        return description;
    }

    public Long getProductId() {
        return productId;
    }

    public String getCreatedAt() {
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return now.format(formatter);
    }
}
