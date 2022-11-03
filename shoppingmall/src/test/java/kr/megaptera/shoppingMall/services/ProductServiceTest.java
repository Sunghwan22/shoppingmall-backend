package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ProductServiceTest {
  private ProductRepository productRepository;
  private ProductService productService;
  private WishRepository wishRepository;
  private UserRepository userRepository;

  @BeforeEach
  public void setup() {
    userRepository = mock(UserRepository.class);
    wishRepository = mock(WishRepository.class);
    productRepository = mock(ProductRepository.class);
    productService = new ProductService(productRepository, wishRepository, userRepository);
  }

  @Test
  void detail() {
    Product product = new Product(1L, 1L, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 5000L, 2L, "상품 설명", 3000L);

    given(productRepository.findById(1L)).willReturn(Optional.of(product));

    Product foundProduct = productService.detail(1L);

    assertThat(foundProduct).isNotNull();
    assertThat(foundProduct.maker()).isEqualTo("애플");
  }
}
