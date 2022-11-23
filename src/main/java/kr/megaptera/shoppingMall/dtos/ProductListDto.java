package kr.megaptera.shoppingMall.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductListDto {
    private Long deliveryFee;
    private int reviewNumber;
    private int wishNumber;
    private Long id;
    private ProductImageDto productImageDto;
    private String name;
    private Long price;
    private Long cumulativeSales;
    private LocalDateTime createdAt;

    public ProductListDto() {
    }

    public ProductListDto(
        Long id,
        ProductImageDto productImageDto,
        String name,
        Long price,
        Long cumulativeSales,
        LocalDateTime createdAt,
        Long deliveryFee,
        int reviewNumber,
        int wishNumber) {

        this.id = id;
        this.productImageDto = productImageDto;
        this.name = name;
        this.price = price;
        this.cumulativeSales = cumulativeSales;
        this.createdAt = createdAt;
        this.deliveryFee = deliveryFee;
        this.reviewNumber = reviewNumber;
        this.wishNumber = wishNumber;
    }

    public int getReviewNumber() {
        return reviewNumber;
    }

    public int getWishNumber() {
        return wishNumber;
    }

    public ProductImageDto getProductImage() {
        return productImageDto;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public Long getCumulativeSales() {
        return cumulativeSales;
    }

    public Long getDeliveryFee() {
        return deliveryFee;
    }

    public Long getId() {
        return id;
    }

    public String getCreatedAt() {
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return now.format(formatter);
    }
}
