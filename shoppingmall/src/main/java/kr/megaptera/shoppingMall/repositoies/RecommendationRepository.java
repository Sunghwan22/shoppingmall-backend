package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
  Optional<Recommendation> findByReviewId(Long reviewId);

  List<Recommendation> findAllByReviewId(Long reviewId);
}
