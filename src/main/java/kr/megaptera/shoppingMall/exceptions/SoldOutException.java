package kr.megaptera.shoppingMall.exceptions;

public class SoldOutException extends RuntimeException {
    public SoldOutException() {
        super("품절된 상품 입니다");
    }
}
