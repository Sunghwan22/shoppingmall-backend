package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Image;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.ProductImageRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

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
    Product product = new Product();

    List<Image> images = List.of(
        new Image(1L, product, "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85" +
            "%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2022-10-20+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+10.55.03.png",
            true));

    given(productImageRepository.findByProductId(1L)).willReturn(images);

    List<Image> foundImages = imageService.list(1L);

    assertThat(foundImages).hasSize(1);
    assertThat(foundImages.get(0).isThumbnailImage()).isEqualTo(true);
  }
}