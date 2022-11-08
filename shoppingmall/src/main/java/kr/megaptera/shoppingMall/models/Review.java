package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ReviewDto;
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

  @ElementCollection
  private List<Recommendation> recommendations = new ArrayList<>();

  @ElementCollection
  private List<ReviewImage> reviewImages = new ArrayList<>();

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

  public Long id() {
    return id;
  }

  public Long productId() {
    return productId;
  }

  public Long rating() {
    return rating;
  }

  public Long userId() {
    return userId;
  }

  public String optionName() {
    return optionName;
  }

  public String content() {
    return content;
  }

  public Boolean isBestReview() {
    return isBestReview;
  }

  public LocalDateTime createdAt() {
    return createdAt;
  }

  public String userNickName() {
    return userNickName;
  }

  public ReviewDto toDto() {
    return new ReviewDto(
        id, productId, rating,
        userId, optionName, content,
        isBestReview, userNickName, reviewImages, recommendations);
  }
}
