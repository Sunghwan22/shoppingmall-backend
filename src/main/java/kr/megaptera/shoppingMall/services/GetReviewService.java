package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.RecommendationDto;
import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.dtos.ReviewImageDto;
import kr.megaptera.shoppingMall.exceptions.ReviewNotFoundException;
import kr.megaptera.shoppingMall.models.Recommendation;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.models.ReviewImage;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetReviewService {
    private final ReviewRepository reviewRepository;

    public GetReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewDto getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(reviewId));

        List<ReviewImageDto> reviewImageDtos = review.images()
            .stream().map(ReviewImage::toDto).toList();

        List<RecommendationDto> recommendationDtos = review.recommendations()
            .stream().map(Recommendation::toDto).toList();

        return review.toDto(reviewImageDtos, recommendationDtos);
    }
}
