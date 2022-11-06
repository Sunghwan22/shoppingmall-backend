package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewService {
  private final ReviewRepository reviewRepository;
  private Pageable pageable;

  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public List<Review> listByProductId(Long productId, Integer page) {
    Sort sort = Sort.by("id");
    pageable = PageRequest.of(page - 1, 8, sort);

    return reviewRepository.findAllByProductId(productId);
  }
}
