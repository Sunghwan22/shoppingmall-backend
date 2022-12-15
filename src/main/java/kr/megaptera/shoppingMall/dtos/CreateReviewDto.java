package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class CreateReviewDto {
    private Long rating;

    private String content;

    private String description;

    private List<String> imageUrl;

    public CreateReviewDto(
        Long rating,
        String content,
        String description,
        List<String> imageUrl) {
        this.rating = rating;
        this.content = content;
        this.description = description;
        this.imageUrl = imageUrl;
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

    public List<String> getImageUrl() {
        return imageUrl;
    }
}
