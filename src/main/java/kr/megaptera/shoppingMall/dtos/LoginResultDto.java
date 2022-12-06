package kr.megaptera.shoppingMall.dtos;

import kr.megaptera.shoppingMall.models.Address;

public class LoginResultDto {
    private String accessToken;

    private String state;

    private String name;

    private String phoneNumber;

    private AddressDto addressDto;
    private String recipient;

    private Long id;

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

    public LoginResultDto(
        String recipient,
        String phoneNumber,
        AddressDto addressDto) {
        this.phoneNumber = phoneNumber;
        this.addressDto = addressDto;
        this.recipient = recipient;
    }

    public LoginResultDto(Long id, String accessToken, String name, String state) {
        this.id = id;
        this.accessToken = accessToken;
        this.name = name;
        this.state = state;
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

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getRecipient() {
        return recipient;
    }
}
