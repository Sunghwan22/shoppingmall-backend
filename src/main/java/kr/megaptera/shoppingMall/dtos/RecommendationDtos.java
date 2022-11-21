package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class RecommendationDtos {
  private List<RecommendationDto> recommendationDtos;

  private int pageNumber;

  public RecommendationDtos() {
  }

  public RecommendationDtos(
      List<RecommendationDto> recommendationDtos)
  {
    this.recommendationDtos = recommendationDtos;
  }

  public List<RecommendationDto> getRecommendations() {
    return recommendationDtos;
  }
}
