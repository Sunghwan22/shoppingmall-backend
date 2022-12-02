package kr.megaptera.shoppingMall.exceptions;

public class NoselectCartItemException extends RuntimeException {
    public NoselectCartItemException() {
        super("선택된 아이템이 없습니다");
    }
}
