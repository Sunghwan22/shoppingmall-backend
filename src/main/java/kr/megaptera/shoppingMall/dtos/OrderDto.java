package kr.megaptera.shoppingMall.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderDto {
    private Long id;

    private Long userId;

    private String name;

    private String phoneNumber;

    private String imageUrl;

    private AddressDto address;

    private Long quantity;

    private Long orderPayment;

    private String deliveryRequest;

    private String detailAddress;

    private String productOptionDescription;

    private String kakaoPayUrl;

    private OrderDto() {
    }

    public OrderDto(Long id,
                    Long userId,
                    String name,
                    String phoneNumber,
                    String imageUrl,
                    AddressDto address,
                    Long quantity,
                    Long orderPayment,
                    String deliveryRequest,
                    String detailAddress,
                    String productOptionDescription,
                    String kakaoPayUrl) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.address = address;
        this.quantity = quantity;
        this.orderPayment = orderPayment;
        this.deliveryRequest = deliveryRequest;
        this.detailAddress = detailAddress;
        this.productOptionDescription = productOptionDescription;
        this.kakaoPayUrl = kakaoPayUrl;
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

    public String getDetailAddress() {
        return detailAddress;
    }

    public String getProductOptionDescription() {
        return productOptionDescription;
    }

    public String getKakaoPayUrl() {
        return kakaoPayUrl;
    }

    public String getCreatedAt() {
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return now.format(formatter);
    }
}
