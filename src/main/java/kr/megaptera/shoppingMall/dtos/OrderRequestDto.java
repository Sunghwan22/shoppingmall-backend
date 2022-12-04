package kr.megaptera.shoppingMall.dtos;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class OrderRequestDto {
    private String name;

    private String phoneNumber;

    private List<OrderProductDto> orderProducts;

    private Long totalOrderPayment;

    private AddressDto address;

    private String deliveryRequest;

    @NotBlank
    private String detailAddress;


    public OrderRequestDto() {
    }

    public OrderRequestDto(
        String name,
        String phoneNumber,
        AddressDto address,
        List<OrderProductDto> orderProducts,
        String deliveryRequest,
        String detailAddress,
        Long totalOrderPayment) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderProducts = orderProducts;
        this.deliveryRequest = deliveryRequest;
        this.detailAddress = detailAddress;
        this.totalOrderPayment = totalOrderPayment;
    }

    public String getName() {
        return name;
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

    public String getDetailAddress() {
        return detailAddress;
    }

    public Long getTotalOrderPayment() {
        return totalOrderPayment;
    }
}
