package kr.megaptera.shoppingMall.exceptions;

public class InquiryContentBlankException extends RuntimeException {
    public InquiryContentBlankException() {
        super("문의할 내용을 입력해주세요");
    }
}
