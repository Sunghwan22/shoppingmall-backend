package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.RecommendationDto;
import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.dtos.ReviewDtos;
import kr.megaptera.shoppingMall.dtos.ReviewImageDto;
import kr.megaptera.shoppingMall.models.Recommendation;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.models.ReviewImage;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



@Service
@Transactional
public class GetReviewsService {
    private final ReviewRepository reviewRepository;
    private Pageable pageable;

    public GetReviewsService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewDtos getReviews(Long productId, Integer page) {
        List<Review> reviewsPerPage = new ArrayList<>();

        List<Review> reviews =
            reviewRepository.findAllByProductId(productId)
                .stream().filter(review -> !review.isBestReview())
                .toList();

        for(int i = 4 * (page - 1); i < page * 4; i+=1) {
            if(reviews.size() == i) {
                break;
            }

            reviewsPerPage.add(reviews.get(i));
        }

        List<ReviewDto> reviewDtos = reviewsPerPage.stream()
            .map(review -> review.toDto(
                reviewImageDtos(review),
                recommendationDtos(review))).toList();

        int pages = reviews.size() / 4;

        if(reviews.size() % 4 != 0) {
            pages = reviews.size() / 4 + 1;
        }

        double totalRating = this.totalRating(productId);

        int totalReviewNumber = reviewRepository.findAllByProductId(productId)
            .size();

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





