package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.repositoies.ProductImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ImageServiceTest {
  private ProductImageRepository productImageRepository;
  private ImageService imageService;

  @BeforeEach
  public void setup() {
    productImageRepository = mock(ProductImageRepository.class);
    imageService = new ImageService(productImageRepository);

  }

  @Test
  void imageList() {
    List<ProductImage> productImages = List.of(
        new ProductImage(1L, 1L, "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85" +
            "%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2022-10-20+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+10.55.03.png",
            true));

    given(productImageRepository.findByProductId(1L)).willReturn(productImages);

    List<ProductImage> foundProductImages = imageService.list(1L);

    assertThat(foundProductImages).hasSize(1);
    assertThat(foundProductImages.get(0).isThumbnailImage()).isEqualTo(true);
  }
}