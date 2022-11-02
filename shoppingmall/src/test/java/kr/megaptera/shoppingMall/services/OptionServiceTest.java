package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.ProductOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OptionServiceTest {
  private ProductOptionRepository productOptionRepository;
  private OptionService optionService;

  @BeforeEach
  public void setup() {
    productOptionRepository = mock(ProductOptionRepository.class);
    optionService = new OptionService(productOptionRepository);

  }

  @Test
  void optionList() {
    Long productId = 1L;

    List<Option> productOptions = List.of(
        new Option(1L, productId, 5000L, "블랙"),
        new Option(2L, productId, 5000L, "화이트"),
        new Option(3L, productId, 5000L, "골드")
    );

    given(productOptionRepository.findByProductId(1L)).willReturn(productOptions);

    List<Option> foundProductOptions = optionService.list(1L);

    assertThat(foundProductOptions).hasSize(3);
  }
}