package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ReviewServiceTest {
  private ReviewService reviewService;
  private ReviewRepository reviewRepository;

  @BeforeEach
  void setup() {
    reviewRepository = mock(ReviewRepository.class);
    reviewService = new ReviewService(reviewRepository);
  }

  @Test
  void listByProductId() {
    Long productId = 1L;

    List<Review> reviews = List.of(
        new Review(1L, productId, 1L, 1L, "블랙", "이것은 리뷰다", true, "닉네임"),
        new Review(2L, productId, 1L, 1L, "블랙", "이것은 리뷰다", false, "닉네임")
    );

    int page = 1;

    Pageable pageable = PageRequest.of(page, 8);

    given(reviewRepository.findAllByProductId(productId, pageable)).
        willReturn(new PageImpl<>(reviews, pageable, 1));

    Page<Review> foundReviews = new PageImpl<>(reviews, pageable, 8);

    assertThat(foundReviews).hasSize(2);
  }

  @Test
  void totalReviewsNumber() {
    Long productId = 1L;

    List<Review> reviews = List.of(
        new Review(1L, productId, 1L, 1L, "블랙", "이것은 리뷰다", true, "닉네임"),
        new Review(2L, productId, 1L, 1L, "블랙", "이것은 리뷰다", false, "닉네임")
    );

    given(reviewRepository.findAllByProductId(productId)).
        willReturn(reviews);

     int totalReviewsNumber = reviewService.totalReviewsNumber(productId);

     assertThat(totalReviewsNumber).isEqualTo(2);
  }

  @Test
  void totalRating() {
    Long productId = 1L;

    List<Review> reviews = List.of(
        new Review(1L, productId, 5L, 1L, "블랙", "이것은 리뷰다", true, "닉네임"),
        new Review(2L, productId, 4L, 1L, "블랙", "이것은 리뷰다", false, "닉네임")
    );

    given(reviewRepository.findAllByProductId(productId)).
        willReturn(reviews);

    double totalRating = reviewService.totalRating(productId);

    assertThat(totalRating).isEqualTo(4.5);
  }

  @Test
  void detail() {
    Long reviewId = 1L;

    Review review =
        new Review(reviewId, 1L, 5L, 1L, "블랙", "이것은 리뷰다", true, "닉네임");

    given(reviewRepository.findById(reviewId))
        .willReturn(Optional.of(review));

    Review foundReview = reviewService.detail(reviewId);

    assertThat(foundReview).isNotNull();
  }

  @Test
  void bestReviews() {
    Long productId = 1L;

    List<Review> reviews = List.of(
        new Review(1L, productId, 5L, 1L, "블랙", "이것은 리뷰다", true, "닉네임"),
        new Review(2L, productId, 4L, 1L, "블랙", "이것은 리뷰다", false, "닉네임")
    );

    given(reviewRepository.findAllByProductId(productId)).
        willReturn(reviews);

    List<Review> bestReviews = reviewService.bestReviewsByProductId(productId);

    assertThat(bestReviews).hasSize(1);
  }
}