package kr.megaptera.shoppingMall.repositoies;


import kr.megaptera.shoppingMall.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
  ProductImage findByProductId(Long productId);

  List<ProductImage> findAllByProductId(Long productId);
}
