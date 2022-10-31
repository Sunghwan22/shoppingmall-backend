package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.ProductOption;
import kr.megaptera.shoppingMall.repositoies.ProductOptionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductOptionService {
  private final ProductOptionRepository productOptionRepository;

  public ProductOptionService(ProductOptionRepository productOptionRepository) {
    this.productOptionRepository = productOptionRepository;
  }

  public List<ProductOption> list(Long productId) {
    return productOptionRepository.findByProductId(productId);
  }
}
