package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.repositoies.ProductOptionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OptionService {
  private final ProductOptionRepository productOptionRepository;

  public OptionService(ProductOptionRepository productOptionRepository) {
    this.productOptionRepository = productOptionRepository;
  }

  public List<Option> list(Long productId) {
    return productOptionRepository.findByProductId(productId);
  }
}
