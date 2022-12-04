package kr.megaptera.shoppingMall.dtos;

public class OrderProductDto {
    private Long id;

    private String image;

    private String optionDescription;

    private String name;

    private Long deliveryFee;

    private Long quantity;

    private Long totalPayment;

    private Long productId;

    public OrderProductDto() {
    }

    public OrderProductDto(
        Long id,
        String image,
        String optionDescription,
        String name,
        Long deliveryFee,
        Long quantity,
        Long totalPayment,
        Long productId) {
        this.id = id;
        this.image = image;
        this.optionDescription = optionDescription;
        this.name = name;
        this.deliveryFee = deliveryFee;
        this.quantity = quantity;
        this.totalPayment = totalPayment;
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public String getName() {
        return name;
    }

    public Long getDeliveryFee() {
        return deliveryFee;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getTotalPayment() {
        return totalPayment;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getId() {
        return id;
    }
}
