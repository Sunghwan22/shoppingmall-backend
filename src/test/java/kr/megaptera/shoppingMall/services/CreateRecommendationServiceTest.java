package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.RecommendationDtos;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CreateRecommendationServiceTest {
    private ReviewRepository reviewRepository;
    private CreateRecommendationService createRecommendationService;

    @BeforeEach
    void setup() {
        reviewRepository = mock(ReviewRepository.class);
        createRecommendationService = new CreateRecommendationService(reviewRepository);
    }

    @Test
    void createRecommendation() {
        Long reviewId = 1L;
        Long userId = 10L;

        Review review = Review.fake(1L);

        given(reviewRepository.findById(1L))
            .willReturn(Optional.of(review));

        RecommendationDtos recommendationDtos =
            createRecommendationService.create(reviewId, userId);

        assertThat(recommendationDtos.getRecommendations()).hasSize(2);
    }

    @Test
    void deleteRecommendation() {
        Long reviewId = 1L;
        Long userId = 1L;

        Review review = Review.fake(1L);

        given(reviewRepository.findById(1L))
            .willReturn(Optional.of(review));

        RecommendationDtos recommendationDtos =
            createRecommendationService.create(reviewId, userId);

        assertThat(recommendationDtos.getRecommendations()).hasSize(0);
    }
}
