package kr.megaptera.shoppingMall.exceptions;

public class AuthenticationError extends RuntimeException {
  public AuthenticationError() {
    super("Authorization Error");
  }
}
