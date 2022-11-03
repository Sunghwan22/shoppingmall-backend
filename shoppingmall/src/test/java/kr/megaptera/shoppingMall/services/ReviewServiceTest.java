package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        new Review(1L, productId, 1L, 1L, "블랙", "이것은 리뷰다", true),
        new Review(2L, productId, 1L, 1L, "블랙", "이것은 리뷰다", false)
    );

    given(reviewRepository.findAllByProductId(productId)).
        willReturn(reviews);

    List<Review> foundReviews = reviewService.listByProductId(productId);

    assertThat(foundReviews).hasSize(2);
  }

}