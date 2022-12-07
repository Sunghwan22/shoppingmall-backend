package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @GeneratedValue
    private Long productNumber;

    private String productName;

    private String maker;

    private String category;

    private Long views;

    private Long purchasesNumber;

    private Long stock;

    private Long maximumQuantity;

    private String description;

    private Long price;

    private Long deliveryFee;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ElementCollection
    List<ProductImage> images = new ArrayList<>();

    @ElementCollection
    List<Option> options = new ArrayList<>();

    public Product() {
    }

    public Product(Long id,
                   Long productNumber,
                   List<ProductImage> images,
                   List<Option> options,
                   String productName,
                   String maker,
                   String category,
                   Long views,
                   Long cumulativeSales,
                   Long price,
                   Long stock,
                   Long maximumQuantity,
                   String description,
                   Long deliveryFee) {
        this.id = id;
        this.productNumber = productNumber;
        this.images = images;
        this.options = options;
        this.productName = productName;
        this.maker = maker;
        this.category = category;
        this.views = views;
        this.purchasesNumber = cumulativeSales;
        this.price = price;
        this.stock = stock;
        this.maximumQuantity = maximumQuantity;
        this.description = description;
        this.deliveryFee = deliveryFee;
    }

    public ProductDto toDto(
        List<OptionDto> optionDtos,
        List<ProductImageDto> productImageDtos) {
        return new ProductDto(
            id, productNumber, productName,
            maker, category,
            views, purchasesNumber,
            price, stock,
            maximumQuantity, description, deliveryFee,
            createdAt, updatedAt, optionDtos, productImageDtos
        );
    }

    public static Product fake(Long productId) {
        List<ProductImage> productImages = List.of(
            new ProductImage("url", true),
            new ProductImage("url", false)
        );

        List<Option> productOptions = List.of(
            new Option(3000L, "상품 설명")
        );

        return new Product(
            productId,
            1L,
            productImages,
            productOptions,
            "아이폰14",
            "애플",
            "전자기기",
            1000L,
            120L,
            1000L,
            5000L,
            2L,
            "상품 설명",
            3000L);
    }

    public Long id() {
        return id;
    }

    public String maker() {
        return maker;
    }

    public List<ProductImage> images() {
        return images;
    }

    public List<Option> options() {
        return options;
    }

    public String name() {
        return productName;
    }

    public Long price() {
        return price;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public Long cumulativeSales() {
        return purchasesNumber;
    }

    public Long deliveryFee() {
        return deliveryFee;
    }

    public void addPurchaseNumber() {
        this.purchasesNumber += 1;
    }

    public void reduceProduct(Long quantity) {
        this.stock -= quantity;
    }

    public Long stock() {
        return stock;
    }

    public void increaseViews() {
        this.views += 1;
    }
}
