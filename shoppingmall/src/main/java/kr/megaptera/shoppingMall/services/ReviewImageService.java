package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.ReviewImage;
import kr.megaptera.shoppingMall.repositoies.ReviewImageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewImageService {
  private final ReviewImageRepository reviewImageRepository;

  public ReviewImageService(ReviewImageRepository reviewImageRepository) {
    this.reviewImageRepository = reviewImageRepository;
  }

  public List<ReviewImage> listByProductId(Long productId, Integer page) {
    return reviewImageRepository.findAllByProductId(productId);
  }
}
