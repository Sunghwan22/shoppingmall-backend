package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class OrderRequestDto {
    private String recipient;

    private String phoneNumber;

    private List<OrderProductDto> orderProducts;

    private Long totalOrderPayment;

    private AddressDto address;

    private String deliveryRequest;

    private String description;

    public OrderRequestDto() {
    }

    public OrderRequestDto(
        String recipient,
        String phoneNumber,
        AddressDto address,
        List<OrderProductDto> orderProducts,
        String deliveryRequest,
        Long totalOrderPayment,
        String description) {
        this.recipient = recipient;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderProducts = orderProducts;
        this.deliveryRequest = deliveryRequest;
        this.totalOrderPayment = totalOrderPayment;
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AddressDto getAddress() {
        return address;
    }

    public List<OrderProductDto> getOrderProducts() {
        return orderProducts;
    }

    public String getDeliveryRequest() {
        return deliveryRequest;
    }

    public Long getTotalOrderPayment() {
        return totalOrderPayment;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getDescription() {
        return description;
    }
}
