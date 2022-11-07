package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
  List<ReviewImage> findAllByProductId(Long productId);

  Optional<ReviewImage> findByReviewId(Long reviewId);
}
