package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.RecommendationDto;
import kr.megaptera.shoppingMall.dtos.RecommendationDtos;
import kr.megaptera.shoppingMall.exceptions.ReviewNotFoundException;
import kr.megaptera.shoppingMall.models.Recommendation;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CreateRecommendationService {
    private final ReviewRepository reviewRepository;

    public CreateRecommendationService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public RecommendationDtos create(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(userId));

        Recommendation foundRecommendation =
            review.recommendations().stream().filter(
                    recommendation -> recommendation.getUserId().equals(userId))
                .findFirst().orElse(null);

        if (foundRecommendation == null) {
            Recommendation recommendation = new Recommendation(userId);

            review.recommendations().add(recommendation);
        }

        if (foundRecommendation != null) {
            review.recommendations().remove(foundRecommendation);
        }

        List<RecommendationDto> recommendationDtos =
            review.recommendations().stream().map(
                Recommendation::toDto).toList();

        return new RecommendationDtos(recommendationDtos);
    }
}
