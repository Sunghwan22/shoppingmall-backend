package kr.megaptera.shoppingMall.repositoies;


import kr.megaptera.shoppingMall.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<Image, Long> {
  List<Image> findByProductId(Long productId);
}
