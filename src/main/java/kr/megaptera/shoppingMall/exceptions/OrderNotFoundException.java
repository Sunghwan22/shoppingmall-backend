package kr.megaptera.shoppingMall.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id) {
        super("해당 주문을 찾을 수 없습니다" + " 주문번호 " + id);
    }
}
