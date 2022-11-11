package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.ReviewDtos;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetBestReviewsServiceTest {
    private GetBestReviewsService getBestReviewsService;
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setup() {
        reviewRepository = mock(ReviewRepository.class);
        getBestReviewsService = new GetBestReviewsService(reviewRepository);
    }

    @Test
    void reviewsWith2reviews() {
        Long productId = 1L;

        List<Review> reviews = List.of(
            Review.fakeBestReview(1L),
            Review.fakeBestReview(2L)
        );

        int page = 1;

        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(page - 1, 4, sort);

        given(reviewRepository.findAllByProductId(productId, pageable)).
            willReturn(new PageImpl<>(reviews, pageable, 1));

        given(reviewRepository.findAllByProductId(productId))
            .willReturn(reviews);

        ReviewDtos reviewDtos = getBestReviewsService.getBestReviews(productId, 1);

        assertThat(reviewDtos.getReviews()).hasSize(2);
        assertThat(reviewDtos.getPages()).isEqualTo(1);
        assertThat(reviewDtos.getTotalRating()).isEqualTo(5);
        assertThat(reviewDtos.getTotalReviewNumber()).isEqualTo(2);
    }

    @Test
    void reviewsWith5Reviews() {
        Long productId = 1L;

        List<Review> reviews = List.of(
            Review.fakeBestReview(1L),
            Review.fakeBestReview(2L),
            Review.fakeBestReview(3L),
            Review.fakeBestReview(4L)
        );

        int page = 1;

        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(page - 1, 4, sort);

        given(reviewRepository.findAllByProductId(productId, pageable)).
            willReturn(new PageImpl<>(reviews, pageable, 1));

        given(reviewRepository.findAllByProductId(productId))
            .willReturn(reviews);

        ReviewDtos reviewDtos = getBestReviewsService.getBestReviews(productId, 1);

        assertThat(reviewDtos.getReviews()).hasSize(4);
        assertThat(reviewDtos.getPages()).isEqualTo(1);
        assertThat(reviewDtos.getTotalRating()).isEqualTo(5);
        assertThat(reviewDtos.getTotalReviewNumber()).isEqualTo(4);
    }
}