package kr.megaptera.shoppingMall.exceptions;

public class CartNotFoundException extends RuntimeException{
  public CartNotFoundException() {
    super("장바구니를 찾을 수 없습니다");
  }
}
