package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.exceptions.ReviewNotFoundException;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.models.ReviewImage;
import kr.megaptera.shoppingMall.repositoies.ReviewImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewImageService {
  private final ReviewImageRepository reviewImageRepository;
  private Pageable pageable;

  public ReviewImageService(ReviewImageRepository reviewImageRepository) {
    this.reviewImageRepository = reviewImageRepository;
  }

  public List<ReviewImage> listByProductId(Long productId) {
    return reviewImageRepository.findAllByProductId(productId);
  }

  public ReviewImage findByReviewId(Long reviewId) {
    return reviewImageRepository.findByReviewId(reviewId)
        .orElseThrow(ReviewNotFoundException::new);
  }

  public List<ReviewImage> listByProductId(Page<Review> reviewList) {
    List<ReviewImage> reviewImages = new ArrayList<>();

    List<Long> reviewIdList = reviewList.stream().map(Review::getId)
        .toList();

    for (Long reviewId : reviewIdList) {

      Optional<ReviewImage> reviewImage =
          reviewImageRepository.findByReviewId(reviewId);

      if (reviewImage.isEmpty()) {
        continue;
      }

      reviewImages.add(reviewImage.get());
    }

    return reviewImages;
  }
}
