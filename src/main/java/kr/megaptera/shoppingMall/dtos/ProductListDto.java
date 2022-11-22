package kr.megaptera.shoppingMall.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductListDto {
    private int reviewNumber;
    private int wishNumber;
    private ProductImageDto productImageDto;
    private String name;
    private Long price;
    private Long cumulativeSales;
    private LocalDateTime createdAt;

    public ProductListDto() {
    }

    public ProductListDto(
        ProductImageDto productImageDto,
        String name,
        Long price,
        Long cumulativeSales,
        LocalDateTime createdAt,
        int reviewNumber,
        int wishNumber) {

        this.productImageDto = productImageDto;
        this.name = name;
        this.price = price;
        this.cumulativeSales = cumulativeSales;
        this.createdAt = createdAt;
        this.reviewNumber = reviewNumber;
        this.wishNumber = wishNumber;
    }

    public int getReviewNumber() {
        return reviewNumber;
    }

    public int getWishNumber() {
        return wishNumber;
    }

    public ProductImageDto getProductImageDto() {
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

    public String getCreatedAt() {
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return now.format(formatter);
    }
}
