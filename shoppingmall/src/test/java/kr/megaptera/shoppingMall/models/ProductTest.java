package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
  @Test
  void toDto() {
    List<Option> options = List.of(
        new Option(3000L, "블랙")
    );

    List<ProductImage> productImages = List.of(
        new ProductImage("url", true)
    );

    List<Wish> wishes = List.of(
        new Wish(1L)
    );

    Product product = new Product(1L, 1L,productImages, options, wishes, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 5000L, 2L, "상품 설명", 3000L);


    ProductDto productDto = product.toDto();

    assertThat(productDto.getMaker()).isEqualTo("애플");
    assertThat(productDto.getId()).isEqualTo(1L);
  }

}