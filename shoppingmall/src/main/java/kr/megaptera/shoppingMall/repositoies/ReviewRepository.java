package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  Page<Review> findAllByProductId(Long productId, Pageable pageable);

  List<Review> findAllByProductId(Long productId);

  Review findByProductId(Long productId);
}
