package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.models.ReviewImage;
import kr.megaptera.shoppingMall.repositoies.ReviewImageRepository;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ReviewImageServiceTest {
  private ReviewImageRepository reviewImageRepository;
  private ReviewImageService reviewImageService;
  private ReviewRepository reviewRepository;

  @BeforeEach
  void setup() {
    reviewRepository = mock(ReviewRepository.class);
    reviewImageRepository = mock(ReviewImageRepository.class);
    reviewImageService = new ReviewImageService(reviewImageRepository);
  }

  @Test
  void listByProductId() {
    Long productId = 1L;

    List<ReviewImage> reviewImages = List.of(
        new ReviewImage(1L, "imageurl", 1L, productId),
        new ReviewImage(2L, "imageurl", 2L, productId)
    );


    given(reviewImageRepository.findAllByProductId(productId))
        .willReturn(reviewImages);

    List<ReviewImage> reviewImageList = reviewImageService.listByProductId(1L);

    assertThat(reviewImageList).hasSize(2);
  }

  @Test
  void listAllByReviewId() {
    Long productId = 1L;
    Long reviewId = 1L;
    Long userId = 1L;

    List<Review> reviews = List.of(
        new Review(reviewId, productId, 1L, userId, "블랙", "이것은 리뷰다", true, "닉네임"),
        new Review(reviewId + 1, productId, 2L, userId, "블랙", "이것은 리뷰다", true, "닉네임")
    );

    int page = 1;

    Pageable pageable = PageRequest.of(page, 8);

    given(reviewRepository.findAllByProductId(productId, pageable))
        .willReturn(new PageImpl<>(reviews, pageable, 8));

    given(reviewImageRepository.findByReviewId(1L))
        .willReturn(Optional.of(new ReviewImage(1L, "url", 1L, 1L)));

    List<ReviewImage> foundRecommendations =
        reviewImageService.listByProductId(new PageImpl<>(reviews, pageable, 8));

    assertThat(foundRecommendations).hasSize(1);
  }
}
