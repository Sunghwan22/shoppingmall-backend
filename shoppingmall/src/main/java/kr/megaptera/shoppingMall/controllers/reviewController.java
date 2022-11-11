package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.RecommendationDtos;
import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.dtos.ReviewDtos;
import kr.megaptera.shoppingMall.services.CreateRecommendationService;
import kr.megaptera.shoppingMall.services.GetBestReviewsService;
import kr.megaptera.shoppingMall.services.GetReviewService;
import kr.megaptera.shoppingMall.services.GetReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final GetReviewsService getReviewsService;
    private final GetReviewService getReviewService;
    private final GetBestReviewsService getBestReviewsService;
    private final CreateRecommendationService createRecommendationService;

    public ReviewController(
        GetReviewsService getReviewsService,
        GetReviewService getReviewService,
        GetBestReviewsService getBestReviewsService,
        CreateRecommendationService createRecommendationService) {
        this.getReviewsService = getReviewsService;
        this.getReviewService = getReviewService;
        this.getBestReviewsService = getBestReviewsService;
        this.createRecommendationService = createRecommendationService;
    }

    @GetMapping("/products/{id}")
    public ReviewDtos reviewList(
        @PathVariable("id") Long productId,
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        return getReviewsService.getReviews(productId, page);
    }

    @GetMapping("best/products/{id}")
    public ReviewDtos bestReviewList(
        @PathVariable("id") Long productId,
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        return getBestReviewsService.getBestReviews(productId, page);
    }

    @GetMapping("/{id}")
    public ReviewDto reviewDetail(
        @PathVariable("id") Long reviewId
    ) {
        return getReviewService.getReview(reviewId);
    }

    @PostMapping("/{id}/recommendations")
    @ResponseStatus(HttpStatus.CREATED)
    public RecommendationDtos Recommendation(
        @PathVariable("id") Long reviewId,
        @RequestAttribute("userId") Long userId
    ) {
        return createRecommendationService.create(reviewId, userId);
    }
}
