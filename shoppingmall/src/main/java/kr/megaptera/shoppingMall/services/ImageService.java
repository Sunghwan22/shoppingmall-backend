package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.exceptions.ProductImageNotFound;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.repositoies.ProductImageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ImageService {
  private final ProductImageRepository productImageRepository;

  public ImageService(ProductImageRepository productImageRepository) {
    this.productImageRepository = productImageRepository;
  }

  public ProductImage findByProductId(Long productId) {
    return productImageRepository.findByProductId(productId)
        .orElseThrow(ProductImageNotFound::new);
  }
}
