package kr.megaptera.shoppingMall.dtos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductDto {
  private Long id;

  private String productName;

  private String maker;

  private String category;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private String lastModifiedDate;

  private Long productNumber;

  private Long views;

  private Long cumulativeSales;

  private Long likes;

  private Long price;

  private Long wishes;

  private Long stock;

  private Long maximumQuantity;

  private List<ProductOptionDto> options;

  private List<ReviewDto> reviews;

  private List<QuestionAndAnswerDto> questionAndAnswers;

  private List<ProductImageDto> productImages;

  private String description;

  public ProductDto(Long id, Long productNumber, String productName, String maker, String category, LocalDateTime createdAt, LocalDateTime updatedAt, Long views, Long cumulativeSales, Long likes, Long price, Long stock, Long maximumQuantity, String description, List<ProductImageDto> productImages, List<ProductOptionDto> productOptions) {
  }

  // todo 상세페이지를 GET 할 때 넘겨줘야 할 것 은 무엇인가
  public ProductDto(Long id,
                    String productName,
                    String maker,
                    String category,
                    String lastModifiedDate,
                    Long productNumber,
                    Long views,
                    Long cumulativeSales,
                    Long likes,
                    Long price,
                    Long wishes,
                    Long stock,
                    Long maximumQuantity,
                    List<ReviewDto> reviews,
                    List<QuestionAndAnswerDto> questionAndAnswers,
                    List<ProductImageDto> productImages) {
    this.id = id;
    this.productName = productName;
    this.maker = maker;
    this.category = category;
    this.lastModifiedDate = lastModifiedDate;
    this.productNumber = productNumber;
    this.views = views;
    this.cumulativeSales = cumulativeSales;
    this.likes = likes;
    this.price = price;
    this.wishes = wishes;
    this.stock = stock;
    this.maximumQuantity = maximumQuantity;
    this.reviews = reviews;
    this.questionAndAnswers = questionAndAnswers;
    this.productImages = productImages;
  }

  public ProductDto(Long id, Long productNumber, String productName, String maker,
                    String category, LocalDateTime createdAt, LocalDateTime updatedAt,
                    Long views, Long cumulativeSales, Long likes, Long price,
                    Long wishes, Long stock, Long maximumQuantity, String description,
                    List<ProductImageDto> productImages,
                    List<ProductOptionDto> productOptions) {

    this.id = id;
    this.productNumber = productNumber;
    this.productName = productName;
    this.maker = maker;
    this.category = category;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.views = views;
    this.cumulativeSales = cumulativeSales;
    this.likes = likes;
    this.price = price;
    this.wishes = wishes;
    this.stock = stock;
    this.maximumQuantity = maximumQuantity;
    this.description = description;
    this.productImages = productImages;
    this.options = productOptions;
  }

  public Long getId() {
    return id;
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

  public String getLastModifiedDate() {
    return lastModifiedDate;
  }

  public Long getProductNumber() {
    return productNumber;
  }

  public Long getViews() {
    return views;
  }

  public Long getCumulativeSales() {
    return cumulativeSales;
  }

  public Long getLikes() {
    return likes;
  }

  public Long getPrice() {
    return price;
  }

  public Long getWishes() {
    return wishes;
  }

  public Long getStock() {
    return stock;
  }

  public Long getMaximumQuantity() {
    return maximumQuantity;
  }

  public List<ReviewDto> getReviews() {
    return reviews;
  }

  public List<QuestionAndAnswerDto> getQuestionAndAnswers() {
    return questionAndAnswers;
  }

  public List<ProductImageDto> getProductImages() {
    return productImages;
  }

  public String getDescription() {
    return description;
  }

  public List<ProductOptionDto> getOptions() {
    return options;
  }

//  public String getCreatedAt() {
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//
//    return createdAt.format(formatter);
//  }
//
//  public String getUpdatedAt() {
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//
//    return updatedAt.format(formatter);
//  }
}
