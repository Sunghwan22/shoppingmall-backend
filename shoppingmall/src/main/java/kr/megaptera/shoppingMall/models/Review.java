package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.RecommendationDto;
import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.dtos.ReviewImageDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Review {
  @Id
  @GeneratedValue
  private Long id;

  private Long productId;

  private Long rating;

  private Long userId;

  private String optionName;

  private String content;

  private String userNickName;

  private Boolean isBestReview;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public Review() {
  }

  public Review(Long id,
                Long productId,
                Long rating,
                Long userId,
                String optionName,
                String content,
                Boolean isBestReview,
                String userNickName) {
    this.id = id;
    this.productId = productId;
    this.rating = rating;
    this.userId = userId;
    this.optionName = optionName;
    this.content = content;
    this.isBestReview = isBestReview;
    this.userNickName = userNickName;
  }

  public Long getId() {
    return id;
  }

  public Long getProductId() {
    return productId;
  }

  public Long getRating() {
    return rating;
  }

  public Long getUserId() {
    return userId;
  }

  public String getOptionName() {
    return optionName;
  }

  public String getContent() {
    return content;
  }

  public Boolean getBestReview() {
    return isBestReview;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public String getUserNickName() {
    return userNickName;
  }

  public ReviewDto toDto() {
    return new ReviewDto(
        id, productId, rating,
        userId, optionName, content,
        isBestReview, userNickName);
  }
}
