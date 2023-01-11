package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class CreateReviewDto {
    private Long rating;

    private String content;

    private String description;

    private Long orderId;

    public CreateReviewDto() {
    }

    public CreateReviewDto(Long rating,
                           String content,
                           String description,
                           Long orderId) {
        this.rating = rating;
        this.content = content;
        this.description = description;
        this.orderId = orderId;
    }

    public Long getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public Long getOrderId() {
        return orderId;
    }
}
