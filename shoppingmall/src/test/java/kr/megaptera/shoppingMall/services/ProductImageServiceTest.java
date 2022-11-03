package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ProductImageServiceTest {
  private ImageRepository imageRepository;
  private ImageService imageService;
  private CartItemRepository cartItemRepository;

  @BeforeEach
  public void setup() {
    imageRepository = mock(ImageRepository.class);
    cartItemRepository = mock(CartItemRepository.class);
    imageService = new ImageService(imageRepository);
  }

  @Test
  void imageList() {
    Long productId = 1L;

    List<ProductImage> productImages = List.of(
        new ProductImage(1L, productId, "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85" +
            "%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2022-10-20+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+10.55.03.png",
            true));

    given(imageRepository.findAllByProductId(1L)).willReturn(productImages);

    List<ProductImage> foundProductImages = imageService.list(1L);

    assertThat(foundProductImages).hasSize(1);
    assertThat(foundProductImages.get(0).getThumbnailImage()).isEqualTo(true);
  }

  @Test
  void findThumbNailImage() {
    Long productId = 1L;

    List<ProductImage> productImages = List.of(
        new ProductImage(1L, productId, "imageUrl", true),
        new ProductImage(2L, productId, "image", false));

    given(imageRepository.findAllByProductId(productId)).willReturn(productImages);


    String thumbNailImage = imageService.findThumbNailImage(1L);

    assertThat(thumbNailImage).isEqualTo("imageUrl");
  }

  @Test
  void findThumbNailImages() {
    Long productId = 1L;

    List<ProductImage> productImages = List.of(
        new ProductImage(1L, productId, "imageUrl", true),
        new ProductImage(2L, productId + 1, "image", true));

    given(imageRepository.findByProductId(1L)).willReturn(productImages.get(0));

    List<CartItem> cartItems =
        List.of(
            new CartItem(1L, 1L, "아이폰14", "애플", "전자기기", 35000L, 5000L, "상품설명"
                , 3000L, 1L, "블랙", 1L),
            new CartItem(2L, 1L, "아이폰14", "애플", "전자기기", 35000L, 5000L, "상품설명"
                , 3000L, 1L, "블랙", 1L)
            );

    given(cartItemRepository.findAll()).willReturn(cartItems);

    List<ProductImage> thumbNailProductImages = imageService.findThumbNailImages(cartItems);

    assertThat(thumbNailProductImages.size()).isEqualTo(2);
  }
}