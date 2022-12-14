package kr.megaptera.shoppingMall.dtos;

public abstract class ErrorDto {
    public static int USER_NOT_FOUND = 1001;
    public static int LOGIN_FAILED = 1002;

    private final Integer code;

    private final String message;

    public ErrorDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
