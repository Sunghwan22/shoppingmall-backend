package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.exceptions.ProductNotFound;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product detail(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(ProductNotFound::new);
  }
}
