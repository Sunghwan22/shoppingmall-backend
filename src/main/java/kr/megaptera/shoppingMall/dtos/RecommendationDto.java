package kr.megaptera.shoppingMall.dtos;

public class RecommendationDto {
    private Long userId;

    public RecommendationDto(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
