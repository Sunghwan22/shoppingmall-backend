package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ProductServiceTest {
  private ProductRepository productRepository;
  private ProductService productService;

  @BeforeEach
  public void setup() {
    productRepository = mock(ProductRepository.class);
    productService = new ProductService(productRepository);
  }

  @Test
  void detail() {
    Product product = new Product(1L, 1L, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 100L, 1500L, 5000L, 2L, "상품 설명");

    given(productRepository.findById(1L)).willReturn(Optional.of(product));

    Product foundProduct = productService.detail(1L);

    assertThat(foundProduct).isNotNull();
    assertThat(foundProduct.maker()).isEqualTo("애플");
  }

}