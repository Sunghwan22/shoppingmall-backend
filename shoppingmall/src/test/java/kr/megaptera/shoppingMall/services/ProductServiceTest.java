package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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
    Product product = Product.fake(1L);

    given(productRepository.findById(1L)).willReturn(Optional.of(product));

    ProductDto foundProduct = productService.detail(1L);

    assertThat(foundProduct).isNotNull();
    assertThat(foundProduct.getMaker()).isEqualTo("애플");
  }
}
