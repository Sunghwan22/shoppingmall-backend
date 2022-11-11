package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.RecommendationDto;
import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.dtos.ReviewDtos;
import kr.megaptera.shoppingMall.dtos.ReviewImageDto;
import kr.megaptera.shoppingMall.models.Recommendation;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.models.ReviewImage;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetBestReviewsService {
    private final ReviewRepository reviewRepository;
    private Pageable pageable;

    public GetBestReviewsService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewDtos getBestReviews(Long productId, Integer page) {
        Sort sort = Sort.by("id");
        pageable = PageRequest.of(page - 1, 4, sort);

        Page<Review> reviews =
            reviewRepository.findAllByProductId(productId, pageable);

        List<ReviewDto> reviewDtos = reviews.stream()
            .filter(review -> review.isBestReview().equals(true))
            .map(review -> review.toDto(
                reviewImageDtos(review),
                recommendationDtos(review))).toList();

        int pages = this.pages(productId);

        double totalRating = this.totalRating(productId);

        int totalReviewNumber = reviewRepository.findAllByProductId(productId).size();

        return new ReviewDtos(reviewDtos, pages, totalRating, totalReviewNumber);
    }

    private List<RecommendationDto> recommendationDtos(Review review) {
        return review.recommendations().stream().map(
            Recommendation::toDto).toList();
    }

    private List<ReviewImageDto> reviewImageDtos(Review review) {
        return review.images().stream().map(
            ReviewImage::toDto).toList();
    }

    public int pages(Long productId) {
        return reviewRepository.findAllByProductId(productId, pageable).getTotalPages();
    }

    public double totalRating(Long productId) {
        List<Review> reviews = reviewRepository.findAllByProductId(productId);

        long totalRatingSum = reviews.stream().mapToLong(Review::rating)
            .sum();

        double totalRating = (double) totalRatingSum / reviews.size();

        return Math.round(totalRating * 100) / 100.0;
    }
}
