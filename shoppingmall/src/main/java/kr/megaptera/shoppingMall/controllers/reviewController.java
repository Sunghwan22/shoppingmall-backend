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
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class reviewController {
  private final ReviewService reviewService;

  public reviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/{id}")
  public ReviewsDto reviewList(
      @PathVariable("id") Long productId,
      @RequestParam(required = false, defaultValue = "1") Integer page
  ) {
    List<Review> review = reviewService.listByProductId(productId, page);

    int pageNumber = reviewService.pages(productId);

    return review.toDto();
  }

//  @GetMapping("best/{id}")
//  public ReviewsDto bestReviewList(
//      @PathVariable("id") Long productId
//  ) {
//
//    List<ReviewDto> bestReviewDtos = reviewService.bestReviewsByProductId(productId)
//        .stream().map(Review::toDto).toList();
//
//    List<RecommendationDto> recommendationDtos
//        = recommendationService
//        .listAllByReviewId(reviewService.bestReviewsByProductId(productId))
//        .stream().map(Recommendation::toDto).toList();
//
//    return new ReviewsDto(bestReviewDtos, recommendationDtos)
//  }

  @GetMapping("/detail/{id}")
  public ReviewDto reviewDetail(
      @PathVariable("id") Long reviewId
  ) {
    ReviewDto reviewDto = reviewService.detail(reviewId).toDto();

    ReviewImageDto reviewImageDto = reviewImageService.findByReviewId(reviewId)
        .toDto();

    List<RecommendationDto> recommendationsDto =
        recommendationService.findByReviewId(reviewId)
            .stream().map(Recommendation::toDto)
            .toList();

    return new ReviewDto(reviewDto, reviewImageDto, recommendationsDto);
  }

  @PostMapping("/recommendation/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public RecommendationDtos Recommendation(
      @PathVariable("id") Long reviewId,
      @RequestAttribute("userId") Long userId,
      @RequestBody ProductIdDto productIdDto
  ) {
    Long productId = productIdDto.getProductId();

    recommendationService.createRecommendation(reviewId, userId);

    List<RecommendationDto> recommendationDtos
        = recommendationService
        .listAllByReviewId(reviewService.listByProductId(productId))
        .stream().map(Recommendation::toDto).toList();

    return new RecommendationDtos(recommendationDtos);
  }
}
