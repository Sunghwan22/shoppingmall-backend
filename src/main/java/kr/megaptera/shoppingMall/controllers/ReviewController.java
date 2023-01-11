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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

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

    @PostMapping(
        value = "/products/{id}/reviews",
        consumes = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(
        @PathVariable("id") Long productId,
        @RequestAttribute("userId") Long userId,
        @RequestPart(value = "review") CreateReviewDto createReviewDto,
        @RequestPart(value = "multipartFiles", required = false) List<MultipartFile> multipartFiles) throws IOException {

        createReviewService.createReview(
            productId,
            userId,
            createReviewDto,
            multipartFiles);
    }
}
