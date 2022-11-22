package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetReviewServiceTest {
    private GetReviewService getReviewService;
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setup() {
        reviewRepository = mock(ReviewRepository.class);
        getReviewService = new GetReviewService(reviewRepository);
    }

    @Test
    void getReview() {
        Review review = Review.fake(1L);

        given(reviewRepository.findById(1L)).
            willReturn(Optional.of(review));

        ReviewDto reviewDto = getReviewService.getReview(1L);

        assertThat(reviewDto).isNotNull();
        assertThat(reviewDto.getId()).isEqualTo(1L);
    }
}
