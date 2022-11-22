package kr.megaptera.shoppingMall.exceptions;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(Long reviewId) {
        super("리뷰를 찾을 수 없습니다 리뷰번호" + reviewId);
    }
}
