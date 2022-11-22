package kr.megaptera.shoppingMall.dtos;

import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.models.Wish;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductDto {
    private Long id;

    private Long productNumber;

    private String productName;

    private String maker;

    private String category;

    private Long views;

    private Long cumulativeSales;

    private Long price;

    private Long stock;

    private Long maximumQuantity;

    private String description;

    private Long deliveryFee;

    private List<OptionDto> options;

    private List<ProductImageDto> productImages;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ProductDto() {
    }

    public ProductDto(Long id,
                      Long productNumber,
                      String productName,
                      String maker,
                      String category,
                      Long views,
                      Long cumulativeSales,
                      Long price,
                      Long stock,
                      Long maximumQuantity,
                      String description,
                      Long deliveryFee,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt,
                      List<OptionDto> options,
                      List<ProductImageDto> productImages) {
        this.id = id;
        this.productNumber = productNumber;
        this.productName = productName;
        this.maker = maker;
        this.category = category;
        this.views = views;
        this.cumulativeSales = cumulativeSales;
        this.price = price;
        this.stock = stock;
        this.maximumQuantity = maximumQuantity;
        this.description = description;
        this.deliveryFee = deliveryFee;
        this.productImages = productImages;
        this.options = options;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public String getMaker() {
        return maker;
    }

    public String getCategory() {
        return category;
    }

    public Long getViews() {
        return views;
    }

    public Long getCumulativeSales() {
        return cumulativeSales;
    }

    public Long getPrice() {
        return price;
    }

    public Long getStock() {
        return stock;
    }

    public Long getMaximumQuantity() {
        return maximumQuantity;
    }

    public String getDescription() {
        return description;
    }

    public Long getDeliveryFee() {
        return deliveryFee;
    }

    public List<OptionDto> getOptions() {
        return options;
    }

    public List<ProductImageDto> getProductImages() {
        return productImages;
    }

    public String getCreatedAt() {
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return now.format(formatter);
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
