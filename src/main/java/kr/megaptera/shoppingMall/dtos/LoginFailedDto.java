package kr.megaptera.shoppingMall.dtos;

public class LoginFailedDto extends ErrorDto{
    public LoginFailedDto(Integer code, String message) {
        super(code, message);
    }
}
