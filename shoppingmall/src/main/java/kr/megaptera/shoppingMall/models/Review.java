package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ReviewDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
                Boolean isBestReview) {
    this.id = id;
    this.productId = productId;
    this.rating = rating;
    this.userId = userId;
    this.optionName = optionName;
    this.content = content;
    this.isBestReview = isBestReview;
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

  public ReviewDto toDto() {
    return new ReviewDto(id, productId, rating, userId, optionName, content, isBestReview);
  }
}
