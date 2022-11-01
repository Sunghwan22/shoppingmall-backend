package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<Option, Long> {
  List<Option> findByProductId(Long productId);
}
