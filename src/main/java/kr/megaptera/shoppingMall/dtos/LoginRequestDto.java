package kr.megaptera.shoppingMall.dtos;

import javax.validation.constraints.NotBlank;

public class LoginRequestDto {
    @NotBlank
    private String identifier;

    @NotBlank
    private String password;

    private LoginRequestDto() {
    }

    public LoginRequestDto(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }
}
