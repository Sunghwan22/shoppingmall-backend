package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.RecommendationDto;
import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.dtos.ReviewImageDto;
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
    private List<ReviewImage> images = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Review() {
    }

    public Review(Long id,
                  Long productId,
                  Long rating,
                  Long userId,
                  String optionName,
                  String content,
                  Boolean isBestReview,
                  String userNickName,
                  List<ReviewImage> reviewImages,
                  List<Recommendation> recommendations) {
        this.id = id;
        this.productId = productId;
        this.rating = rating;
        this.userId = userId;
        this.optionName = optionName;
        this.content = content;
        this.isBestReview = isBestReview;
        this.userNickName = userNickName;
        this.images = reviewImages;
        this.recommendations = recommendations;
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

    public static Review fake(Long reviewId) {
        Long fakeProductId = 1L;
        Long fakeRating = 5L;
        Long fakeUserId = 1L;

        List<Recommendation> recommendationList = new ArrayList<>();

        recommendationList.add(new Recommendation(fakeUserId));

        List<ReviewImage> reviewImages = List.of(
            new ReviewImage("imageUrl")
        );

        return new Review(
            reviewId,
            fakeProductId,
            fakeRating,
            fakeUserId,
            "옵션 설명",
            "내용",
            false,
            "유저이름",
            reviewImages,
            recommendationList
        );
    }

    public static Review fakeBestReview(Long reviewId) {
        Long fakeProductId = 1L;
        Long fakeRating = 5L;
        Long fakeUserId = 1L;

        List<Recommendation> recommendationList = List.of(
            new Recommendation(fakeUserId)
        );

        List<ReviewImage> reviewImages = List.of(
            new ReviewImage("imageUrl")
        );

        return new Review(
            reviewId,
            fakeProductId,
            fakeRating,
            fakeUserId,
            "옵션 설명",
            "내용",
            true,
            "유저이름",
            reviewImages,
            recommendationList
        );
    }


    public List<Recommendation> recommendations() {
        return recommendations;
    }

    public List<ReviewImage> images() {
        return images;
    }

    public ReviewDto toDto(
        List<ReviewImageDto> reviewImageDtos,
        List<RecommendationDto> recommendationDtos
    ) {
        return new ReviewDto(
            id, productId, rating,
            userId, optionName, content,
            isBestReview, userNickName, reviewImageDtos, recommendationDtos);
    }
}
