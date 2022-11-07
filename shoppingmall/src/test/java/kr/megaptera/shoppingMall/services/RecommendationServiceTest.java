package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Recommendation;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.RecommendationRepository;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RecommendationServiceTest {
  private ReviewRepository reviewRepository;
  private RecommendationRepository recommendationRepository;
  private RecommendationService recommendationService;

  @BeforeEach
  void setup() {
    reviewRepository = mock(ReviewRepository.class);
    recommendationRepository = mock(RecommendationRepository.class);
    recommendationService = new RecommendationService(recommendationRepository);
  }


  @Test
  void listAllByReviewId() {
    Long productId = 1L;
    Long reviewId = 1L;
    Long userId = 1L;

    List<Review> reviews = List.of(
        new Review(reviewId, productId, 1L, userId, "블랙", "이것은 리뷰다", true, "닉네임")
    );

    int page = 1;

    Pageable pageable = PageRequest.of(page, 8);

    given(reviewRepository.findAllByProductId(productId, pageable))
        .willReturn(new PageImpl<>(reviews, pageable, 8));

    given(recommendationRepository.findByReviewId(reviewId))
        .willReturn(Optional.of(new Recommendation(1L, userId, reviewId)));

    List<Recommendation> foundRecommendations = recommendationService.listAllByReviewId(reviews);

    assertThat(foundRecommendations).hasSize(1);
  }

  @Test
  void createRecommendation() {
    Long userId = 1L;
    Long reviewId = 1L;

    List<Recommendation> recommendations = List.of(
        new Recommendation(1L, userId, reviewId),
        new Recommendation(2L, userId + 1, reviewId)
    );

    given(recommendationRepository.findAllByReviewId(reviewId))
        .willReturn(recommendations);

    recommendationService.createRecommendation(reviewId, userId + 2);

    verify(recommendationRepository).save(any(Recommendation.class));
  }

  @Test
  void alreadyAddRecommendation() {
    Long userId = 1L;
    Long reviewId = 1L;

    List<Recommendation> recommendations = List.of(
        new Recommendation(1L, userId, reviewId),
        new Recommendation(2L, userId + 1, reviewId)
    );

    given(recommendationRepository.findAllByReviewId(reviewId))
        .willReturn(recommendations);

    recommendationService.createRecommendation(reviewId, userId);

    verify(recommendationRepository).delete(any(Recommendation.class));
  }
}
