package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.exceptions.ProductImageNotFound;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.repositoies.ProductImageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ImageService {
  private final ProductImageRepository productImageRepository;

  public ImageService(ProductImageRepository productImageRepository) {
    this.productImageRepository = productImageRepository;
  }

  public List<ProductImage> list(Long productId) {
    return productImageRepository.findByProductId(productId);
  }
}
