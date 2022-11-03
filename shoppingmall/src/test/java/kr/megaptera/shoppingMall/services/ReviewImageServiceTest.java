package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.ReviewImage;
import kr.megaptera.shoppingMall.repositoies.ReviewImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ReviewImageServiceTest {
  private ReviewImageRepository reviewImageRepository;
  private ReviewImageService reviewImageService;

  @BeforeEach
  void setup() {
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

}