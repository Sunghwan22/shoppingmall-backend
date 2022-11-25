package kr.megaptera.shoppingMall.exceptions;

public class LoginFailed extends RuntimeException {
    public LoginFailed() {
        super("일치하는 아이디가 없습니다");
    }
}
