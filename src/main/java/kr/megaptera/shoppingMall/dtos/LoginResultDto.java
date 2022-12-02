package kr.megaptera.shoppingMall.dtos;

import kr.megaptera.shoppingMall.models.Address;

public class LoginResultDto {
    private String accessToken;

    private String name;

    private String phoneNumber;

    private AddressDto addressDto;

    public LoginResultDto() {
    }

    public LoginResultDto(
        String accessToken,
        String name,
        String phoneNumber,
        AddressDto addressDto) {
        this.accessToken = accessToken;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressDto = addressDto;
    }

    public LoginResultDto(String name, String phoneNumber, AddressDto addressDto) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressDto = addressDto;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AddressDto getAddress() {
        return addressDto;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
