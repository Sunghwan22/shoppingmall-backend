package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.models.ProductOption;
import kr.megaptera.shoppingMall.repositoies.ProductImageRepository;
import kr.megaptera.shoppingMall.repositoies.ProductOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ProductOptionServiceTest {
  private ProductOptionRepository productOptionRepository;
  private ProductOptionService productOptionService;

  @BeforeEach
  public void setup() {
    productOptionRepository = mock(ProductOptionRepository.class);
    productOptionService = new ProductOptionService(productOptionRepository);

  }

  @Test
  void optionList() {
    List<ProductOption> productOptions = List.of(
        new ProductOption(1L, 1L, 5000L, "블랙"),
        new ProductOption(2L, 1L, 5000L, "화이트"),
        new ProductOption(3L, 1L, 5000L, "골드")
    );

    given(productOptionRepository.findByProductId(1L)).willReturn(productOptions);

    List<ProductOption> foundProductOptions = productOptionService.list(1L);

    assertThat(foundProductOptions).hasSize(3);
  }
}