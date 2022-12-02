package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.RecommendationDto;
import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.dtos.ReviewDtos;
import kr.megaptera.shoppingMall.dtos.ReviewImageDto;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import kr.megaptera.shoppingMall.services.CreateRecommendationService;
import kr.megaptera.shoppingMall.services.GetBestReviewsService;
import kr.megaptera.shoppingMall.services.GetReviewService;
import kr.megaptera.shoppingMall.services.GetReviewsService;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private JwtUtil jwtUtil;

    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private GetReviewsService getReviewsService;

    @MockBean
    private GetReviewService getReviewService;

    @MockBean
    private GetBestReviewsService getBestReviewsService;

    @MockBean
    private CreateRecommendationService createRecommendationService;

    @Test
    void getReviews() throws Exception {
        List<ReviewImageDto> reviewImageDtos = List.of(
            new ReviewImageDto("imageUrl")
        );

        List<RecommendationDto> recommendationDtos = List.of(
            new RecommendationDto(1L)
        );

        List<ReviewDto> reviewDtos = List.of(
            Review.fake(1L).toDto(reviewImageDtos, recommendationDtos),
            Review.fake(2L).toDto(reviewImageDtos, recommendationDtos)
        );

        given(getReviewsService.getReviews(1L, 1))
            .willReturn(new ReviewDtos(reviewDtos, 1, 5.0, 2));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1/reviews"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("")));

        verify(getReviewsService).getReviews(1L, 1);
    }

    @Test
    void getReview() throws Exception {
        Long reviewId = 1L;

        List<ReviewImageDto> reviewImageDtos = List.of(
            new ReviewImageDto("imageUrl")
        );

        List<RecommendationDto> recommendationDtos = List.of(
            new RecommendationDto(1L)
        );

        ReviewDto reviewDto = Review.fake(reviewId)
            .toDto(reviewImageDtos, recommendationDtos);

        given(getReviewService.getReview(reviewId))
            .willReturn(reviewDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/reviews/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("")));

        verify(getReviewService).getReview(reviewId);
    }

    @Test
    void getBestReviews() throws Exception {
        int page = 1;

        List<ReviewImageDto> reviewImageDtos = List.of(
            new ReviewImageDto("imageUrl")
        );

        List<RecommendationDto> recommendationDtos = List.of(
            new RecommendationDto(1L)
        );

        List<ReviewDto> reviewDtos = List.of(
            Review.fakeBestReview(1L).toDto(reviewImageDtos, recommendationDtos),
            Review.fakeBestReview(2L).toDto(reviewImageDtos, recommendationDtos)
        );

        given(getReviewsService.getReviews(1L, page))
            .willReturn(new ReviewDtos(reviewDtos, 1, 5.0, 2));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1/reviews/best"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("")));

        verify(getBestReviewsService).getBestReviews(1L , page);
    }

    @Test
    void recommendation() throws Exception {
        Long reviewId = 1L;
        Long userId = 1L;

        String accessToken = jwtUtil.encode(1L);

        List<ReviewImageDto> reviewImageDtos = List.of(
            new ReviewImageDto("imageUrl")
        );

        List<RecommendationDto> recommendationDtos = List.of(
            new RecommendationDto(1L)
        );

        ReviewDto reviewDto = Review.fake(reviewId)
            .toDto(reviewImageDtos, recommendationDtos);

        given(getReviewService.getReview(reviewId))
            .willReturn(reviewDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/reviews/1/recommendations")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("")));

        verify(createRecommendationService).create(reviewId, userId);
    }
}