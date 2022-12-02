package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.OrderDto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String name;

    private String phoneNumber;

    private String imageUrl;

    @Embedded
    private Address address;

    private Long quantity;

    private Long orderPayment;

    private String deliveryRequest;

    private String detailAddress;
    private String productOptionDescription;
    private String kakaoPayUrl;

    private Order() {
    }

    public Order(
        Long userId,
        String name,
        String phoneNumber,
        String imageUrl,
        Address address,
        Long quantity,
        Long orderPayment,
        String deliveryRequest,
        String detailAddress,
        String productOptionDescription) {
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
    }

    public OrderDto toDto(String kakaoPayUrl) {
        return new OrderDto(
            id,
            userId,
            name,
            phoneNumber,
            imageUrl,
            address.toDto(),
            quantity,
            orderPayment,
            deliveryRequest,
            detailAddress,
            productOptionDescription,
            kakaoPayUrl
        );
    }

    public Long id() {
        return id;
    }
}
