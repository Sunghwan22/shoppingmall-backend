package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.ReviewDtos;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetReviewsServiceTest {
    private GetReviewsService getReviewsService;
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setup() {
        reviewRepository = mock(ReviewRepository.class);
        getReviewsService = new GetReviewsService(reviewRepository);
    }

    @Test
    void reviewsWith2reviews() {
        Long productId = 1L;

        List<Review> reviews = List.of(
            Review.fake(1L),
            Review.fake(2L)
        );

        int page = 1;

        given(reviewRepository.findAllByProductId(1L))
            .willReturn(reviews);

        ReviewDtos reviewDtos = getReviewsService.getReviews(productId, page);

        assertThat(reviewDtos.getReviews()).hasSize(2);
        assertThat(reviewDtos.getPages()).isEqualTo(1);
        assertThat(reviewDtos.getTotalRating()).isEqualTo(5);
        assertThat(reviewDtos.getTotalReviewNumber()).isEqualTo(2);
    }

    @Test
    void reviewsWith5Reviews() {
        Long productId = 1L;

        List<Review> reviews = List.of(
            Review.fake(1L),
            Review.fake(2L),
            Review.fake(3L),
            Review.fake(4L),
            Review.fake(5L)
        );

        int page = 1;

        given(reviewRepository.findAllByProductId(1L))
            .willReturn(reviews);

        ReviewDtos reviewDtos = getReviewsService.getReviews(productId, page);

        assertThat(reviewDtos.getReviews()).hasSize(4);
        assertThat(reviewDtos.getPages()).isEqualTo(2);
        assertThat(reviewDtos.getTotalRating()).isEqualTo(5);
        assertThat(reviewDtos.getTotalReviewNumber()).isEqualTo(5);
    }

    @Test
    void reviewsWith5ReviewsWithPage2() {
        Long productId = 1L;

        List<Review> reviews = List.of(
            Review.fake(1L),
            Review.fake(2L),
            Review.fake(3L),
            Review.fake(4L),
            Review.fake(5L)
        );

        int page = 2;

        given(reviewRepository.findAllByProductId(1L))
            .willReturn(reviews);

        ReviewDtos reviewDtos = getReviewsService.getReviews(productId, page);

        assertThat(reviewDtos.getReviews()).hasSize(1);
        assertThat(reviewDtos.getPages()).isEqualTo(2);
        assertThat(reviewDtos.getTotalRating()).isEqualTo(5);
        assertThat(reviewDtos.getTotalReviewNumber()).isEqualTo(5);
    }

    @Test
    void totalRating() {
        Long productId = 1L;

        List<Review> reviews = List.of(
            Review.fake(1L),
            Review.fake(2L)
        );

        given(reviewRepository.findAllByProductId(productId)).
            willReturn(reviews);

        double totalRating = getReviewsService.totalRating(productId);

        assertThat(totalRating).isEqualTo(5.0);
    }
}
