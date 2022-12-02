package kr.megaptera.shoppingMall.dtos;

public class OrderRequestDto {
    private String name;

    private String phoneNumber;

    private Long productId;

    private AddressDto address;

    private Long quantity;

    private Long orderPayment;

    private String deliveryRequest;

    private String detailAddress;

    private String productOptionDescription;

    public OrderRequestDto() {
    }

    public OrderRequestDto(String name,
                           String phoneNumber,
                           Long productId,
                           AddressDto address,
                           Long quantity,
                           Long orderPayment,
                           String deliveryRequest,
                           String detailAddress,
                           String productOptionDescription) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.productId = productId;
        this.address = address;
        this.quantity = quantity;
        this.orderPayment = orderPayment;
        this.deliveryRequest = deliveryRequest;
        this.detailAddress = detailAddress;
        this.productOptionDescription = productOptionDescription;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getProductId() {
        return productId;
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
}
