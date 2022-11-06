package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.RecommendationDto;
import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.dtos.ReviewImageDto;
import kr.megaptera.shoppingMall.dtos.WishDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
  @Test
  void toDto() {
    Product product = new Product(1L, 1L, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 5000L, 2L, "상품 설명", 3000L);

    List<ProductImageDto> productImages = List.of(
        new ProductImageDto(1L, "imageUrl", true, 1L)
    );

    List<OptionDto> productOptions = List.of(
        new OptionDto(1L, 1L, 5000L, "블랙"),
        new OptionDto(2L, 1L, 5000L, "화이트"),
        new OptionDto(3L, 1L, 5000L, "골드")
    );

    List<WishDto> wishes = List.of(
        new WishDto(1L, 1L, 1L)
    );

    List<ReviewDto> reviews = List.of(
        new ReviewDto(1L, 1L, 1L, 1L, "블랙", "이것은 리뷰다", true, "압도적승리감")
    );

    List<ReviewImageDto> reviewImages = List.of(
        new ReviewImageDto(1L, 1L, 1L, "imageUrl")
    );

    List<RecommendationDto> recommendationDtos = List.of(
        new RecommendationDto(1L, 1L, 1L)
    );

    ProductDto productDto = product.toDto(productImages, productOptions, wishes);

    assertThat(productDto.getMaker()).isEqualTo("애플");
    assertThat(productDto.getId()).isEqualTo(1L);
  }

}