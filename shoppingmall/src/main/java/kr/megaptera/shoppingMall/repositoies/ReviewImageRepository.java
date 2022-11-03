package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
  List<ReviewImage> findAllByProductId(Long productId);
}
