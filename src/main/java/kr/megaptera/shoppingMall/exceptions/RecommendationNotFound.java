package kr.megaptera.shoppingMall.exceptions;

public class RecommendationNotFound extends RuntimeException{
  public RecommendationNotFound() {
    super("추천을 찾을수가 없습니다");
  }
}
