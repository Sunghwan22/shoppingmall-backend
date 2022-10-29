package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
  @Test
  void toDto() {
    Product product = new Product(1L, 1L, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 100L, 1500L, 5000L, 2L, "상품 설명");

    ProductDto productDto = product.toDto();

    assertThat(productDto.getMaker()).isEqualTo("애플");
    assertThat(productDto.getId()).isEqualTo(1L);
  }

}