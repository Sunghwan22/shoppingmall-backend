package kr.megaptera.shoppingMall.dtos;

public class CartItemNotFound extends RuntimeException{
    public CartItemNotFound() {
        super("상품을 찾을 수 없습니다");
    }
}
