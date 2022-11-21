package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class ReviewDtos {

    private int pages;

    private double totalRating;

    private int totalReviewNumber;

    private List<ReviewDto> reviews;

    public ReviewDtos(List<ReviewDto> reviewDtos) {
        this.reviews = reviewDtos;
    }

    public ReviewDtos(List<ReviewDto> reviews,
                      int pages,
                      double totalRating,
                      int totalReviewNumber) {
        this.reviews = reviews;
        this.pages = pages;
        this.totalRating = totalRating;
        this.totalReviewNumber = totalReviewNumber;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public int getPages() {
        return pages;
    }

    public double getTotalRating() {
        return totalRating;
    }

    public int getTotalReviewNumber() {
        return totalReviewNumber;
    }
}
