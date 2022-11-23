package kr.megaptera.shoppingMall.dtos;

public class UserNotFoundExceptionDto extends ErrorDto{

    public UserNotFoundExceptionDto(Integer code, String message) {
        super(code, message);
    }
}
