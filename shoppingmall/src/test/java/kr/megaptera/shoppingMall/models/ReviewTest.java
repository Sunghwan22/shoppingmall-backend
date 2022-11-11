package kr.megaptera.shoppingMall.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewTest {
    @Test
    void fake() {
        Review review = Review.fake(1L);

        assertThat(review).isNotNull();
        assertThat(review.id()).isEqualTo(1L);
        assertThat(review.isBestReview()).isEqualTo(false);
    }

    @Test
    void bestReviewFake() {
        Review review = Review.fakeBestReview(1L);

        assertThat(review).isNotNull();
        assertThat(review.id()).isEqualTo(1L);
        assertThat(review.isBestReview()).isEqualTo(true);
    }
}