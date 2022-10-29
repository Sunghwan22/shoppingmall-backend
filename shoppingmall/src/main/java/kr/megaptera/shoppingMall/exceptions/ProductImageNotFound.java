package kr.megaptera.shoppingMall.exceptions;

public class ProductImageNotFound extends RuntimeException{
  public ProductImageNotFound() {
    super("상품 이미지를 찾을 수 없습니다");
  }
}
