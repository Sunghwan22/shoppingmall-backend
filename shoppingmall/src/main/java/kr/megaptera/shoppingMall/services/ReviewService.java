package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.exceptions.ReviewNotFoundException;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ReviewService {
  private final ReviewRepository reviewRepository;
  private Pageable pageable;

  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public Page<Review> listByProductId(Long productId, Integer page) {
    Sort sort = Sort.by("id");
    pageable = PageRequest.of(page - 1, 4, sort);

    return reviewRepository.findAllByProductId(productId, pageable);
  }

  public int pages(Long productId) {
    return reviewRepository.findAllByProductId(productId, pageable).getTotalPages();
  }

  public List<Review> listByProductId(Long productId) {
    return reviewRepository.findAllByProductId(productId);
  }

  public int totalReviewsNumber(Long productId) {
    return reviewRepository.findAllByProductId(productId).size();
  }

  public double totalRating(Long productId) {
    List<Review> reviews = reviewRepository.findAllByProductId(productId);

    long totalRatingSum = reviews.stream().mapToLong(Review::getRating)
        .sum();

    double totalRating = (double) totalRatingSum / reviews.size();

    return Math.round(totalRating * 100) / 100.0;
  }

  public Review detail(Long reviewId) {
    return reviewRepository.findById(reviewId)
        .orElseThrow(ReviewNotFoundException::new);
  }

  public List<Review> bestReviewsByProductId(Long productId) {
    List<Review> reviews= reviewRepository.findAllByProductId(productId);

    return reviews.stream().filter(review -> review.getBestReview().equals(true))
        .toList();
  }
}
