package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.ProductIdDto;
import kr.megaptera.shoppingMall.dtos.RecommendationDto;
import kr.megaptera.shoppingMall.dtos.RecommendationDtos;
import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.dtos.ReviewImageDto;
import kr.megaptera.shoppingMall.dtos.ReviewsDto;
import kr.megaptera.shoppingMall.models.Recommendation;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.models.ReviewImage;
import kr.megaptera.shoppingMall.services.RecommendationService;
import kr.megaptera.shoppingMall.services.ReviewImageService;
import kr.megaptera.shoppingMall.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class reviewController {
  private final ReviewService reviewService;
  private final ReviewImageService reviewImageService;
  private final RecommendationService recommendationService;

  public reviewController(ReviewService reviewService,
                          ReviewImageService reviewImageService,
                          RecommendationService recommendationService) {
    this.reviewService = reviewService;
    this.reviewImageService = reviewImageService;
    this.recommendationService = recommendationService;
  }

  @GetMapping("/{id}")
  public ReviewsDto reviewList(
      @PathVariable("id") Long productId,
      @RequestParam(required = false, defaultValue = "1") Integer page
  ) {
    List<ReviewDto> reviewDtos = reviewService.listByProductId(productId,page)
        .stream().map(Review::toDto).toList();

    List<ReviewImageDto> reviewImageDtos = reviewImageService.listByProductId(productId,page)
        .stream().map(ReviewImage::toDto).toList();

    List<RecommendationDto> recommendationDtos
        = recommendationService.listAllByReviewId(reviewService.listByProductId(productId,page))
        .stream().map(Recommendation::toDto).toList();

    return new ReviewsDto(reviewDtos, reviewImageDtos, recommendationDtos);
  }

  @PostMapping("/recommendation/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public RecommendationDtos Recommendation(
      @PathVariable("id") Long reviewId,
      @RequestAttribute("userId") Long userId,
      @RequestBody ProductIdDto productIdDto
   ) {
    recommendationService.createRecommendation(reviewId, userId);

    Long productId = productIdDto.getProductId();

    List<RecommendationDto> recommendationDtos
        = recommendationService
        .listAllByReviewId(reviewService.listByProductId(productId))
        .stream().map(Recommendation::toDto).toList();

    return new RecommendationDtos(recommendationDtos);
  }
}
