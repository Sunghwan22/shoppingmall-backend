package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
  List<ProductOption> findByProductId(Long productId);
}