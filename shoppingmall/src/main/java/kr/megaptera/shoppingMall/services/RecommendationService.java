package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.RecommendationDto;
import kr.megaptera.shoppingMall.models.Recommendation;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.RecommendationRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecommendationService {
  private final RecommendationRepository recommendationRepository;

  public RecommendationService(RecommendationRepository recommendationRepository) {
    this.recommendationRepository = recommendationRepository;
  }

  public List<Recommendation> listAllByReviewId(Page<Review> reviewList) {
    List<Recommendation> recommendations = new ArrayList<>();

    List<Long> reviewIdList = reviewList.stream().map(Review::getId)
        .toList();

    for (Long reviewId : reviewIdList) {

      Optional<Recommendation> recommendation =
          recommendationRepository.findByReviewId(reviewId);

      if (recommendation.isEmpty()) {
        continue;
      }

      recommendations.add(recommendation.get());
    }

    return recommendations;
  }

  public List<Recommendation> listAllByReviewId(List<Review> reviewList) {
    List<Recommendation> recommendations = new ArrayList<>();

    List<Long> reviewIdList = reviewList.stream().map(Review::getId)
        .toList();

    for (Long reviewId : reviewIdList) {

      Optional<Recommendation> recommendation =
          recommendationRepository.findByReviewId(reviewId);

      if (recommendation.isEmpty()) {
        continue;
      }

      recommendations.add(recommendation.get());
    }

    return recommendations;
  }

  public List<RecommendationDto> createRecommendation(Long reviewId, Long userId) {
    List<Recommendation> recommendations = recommendationRepository
        .findAllByReviewId(reviewId);

    Recommendation foundRecommendation = recommendations.stream()
        .filter(recommendation -> recommendation.getUserId().equals(userId))
        .findFirst().orElse(null);

    if (foundRecommendation == null) {
      Recommendation recommendation = new Recommendation(userId, reviewId);
      recommendationRepository.save(recommendation);
    }

    if (foundRecommendation != null) {
      recommendationRepository.delete(foundRecommendation);
    }

    return recommendationRepository
        .findAllByReviewId(reviewId).stream().map(
            Recommendation::toDto).toList();
  }

  public List<Recommendation> findByReviewId(Long reviewId) {
    return recommendationRepository.findAllByReviewId(reviewId);
  }
}
