package kr.megaptera.shoppingMall.repositoies;


import kr.megaptera.shoppingMall.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
  Optional<ProductImage> findByProductId(Long productId);
}
