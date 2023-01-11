package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.OrderDto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    public static final String REVIEW_WRITING_COMPLETE = "REVIEW_WRITING_COMPLETE";
    public static final String NO_REVIEW = "NO_REVIEW";

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long productId;

    private String name;

    private String phoneNumber;

    private String imageUrl;

    @Embedded
    private Address address;

    private Long quantity;

    private Long orderPayment;

    private String deliveryRequest;

    private String description;

    private String state;

    private Order() {
    }

    public Order(
        Long userId,
        Long productId,
        String name,
        String phoneNumber,
        String imageUrl,
        Address address,
        Long quantity,
        Long orderPayment,
        String deliveryRequest,
        String description,
        String state) {
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
        this.state = state;
    }

    public OrderDto toDto() {
        return new OrderDto(
            id,
            productId,
            userId,
            name,
            phoneNumber,
            imageUrl,
            address.toDto(),
            quantity,
            orderPayment,
            deliveryRequest,
            description
        );
    }

    public String state() {
        return state;
    }

    public Long id() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public static Order fake(String reviewState) {
        return new Order(
            1L,
            1L,
            "아이폰 14",
            "010-3144-7938",
            "imageUrl",
            new Address(44637L, "도로명주소", "지번주소", "상세주소"),
            1L,
            10000L,
            "배송요청사항",
            "옵션명블랙",
            reviewState
        );
    }

    public void changeState() {
        if(Objects.equals(this.state, NO_REVIEW)) {
            this.state = REVIEW_WRITING_COMPLETE;
        }

        if(Objects.equals(this.state, REVIEW_WRITING_COMPLETE)) {
            this.state = NO_REVIEW;
        }
    }
}
