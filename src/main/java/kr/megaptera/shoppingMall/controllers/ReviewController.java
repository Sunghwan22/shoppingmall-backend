package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.CreateReviewDto;
import kr.megaptera.shoppingMall.dtos.RecommendationDtos;
import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.dtos.ReviewDtos;
import kr.megaptera.shoppingMall.services.CreateRecommendationService;
import kr.megaptera.shoppingMall.services.CreateReviewService;
import kr.megaptera.shoppingMall.services.GetBestReviewsService;
import kr.megaptera.shoppingMall.services.GetReviewService;
import kr.megaptera.shoppingMall.services.GetReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    private final GetReviewsService getReviewsService;
    private final GetReviewService getReviewService;
    private final GetBestReviewsService getBestReviewsService;
    private final CreateRecommendationService createRecommendationService;
    private final CreateReviewService createReviewService;

    public ReviewController(
        GetReviewsService getReviewsService,
        GetReviewService getReviewService,
        GetBestReviewsService getBestReviewsService,
        CreateRecommendationService createRecommendationService,
        CreateReviewService createReviewService) {
        this.getReviewsService = getReviewsService;
        this.getReviewService = getReviewService;
        this.getBestReviewsService = getBestReviewsService;
        this.createRecommendationService = createRecommendationService;
        this.createReviewService = createReviewService;
    }

    @GetMapping("/products/{id}/reviews")
    public ReviewDtos reviewList(
        @PathVariable("id") Long productId,
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        return getReviewsService.getReviews(productId, page);
    }

    @GetMapping("/products/{id}/reviews/best")
    public ReviewDtos bestReviewList(
        @PathVariable("id") Long productId,
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        return getBestReviewsService.getBestReviews(productId, page);
    }

    @GetMapping("/reviews/{id}")
    public ReviewDto reviewDetail(
        @PathVariable("id") Long reviewId
    ) {
        return getReviewService.getReview(reviewId);
    }

    @PostMapping("/reviews/{id}/recommendations")
    @ResponseStatus(HttpStatus.CREATED)
    public RecommendationDtos Recommendation(
        @PathVariable("id") Long reviewId,
        @RequestAttribute("userId") Long userId
    ) {
        return createRecommendationService.create(reviewId, userId);
    }

    @PostMapping("//products/{id}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(
        @PathVariable("id") Long productId,
        @RequestAttribute("userId") Long userId,
        @RequestBody CreateReviewDto createReviewDto
    ) {
        createReviewService.createReview(
            productId,
            userId,

            );
    }

}
