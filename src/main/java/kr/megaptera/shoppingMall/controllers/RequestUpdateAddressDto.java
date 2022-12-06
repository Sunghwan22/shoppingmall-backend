package kr.megaptera.shoppingMall.controllers;

public class RequestUpdateAddressDto {
    private String recipient;

    private String phoneNumber;

    private String detailAddress;

    private Long zonecode;

    private String roadAddress;

    private String jibunAddress;

    private RequestUpdateAddressDto() {
    }

    public RequestUpdateAddressDto(
        String recipient,
        String phoneNumber,
        String detailAddress,
        Long zonecode,
        String roadAddress,
        String jibunAddress) {
        this.recipient = recipient;
        this.phoneNumber = phoneNumber;
        this.detailAddress = detailAddress;
        this.zonecode = zonecode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public Long getZonecode() {
        return zonecode;
    }

    public String getRoadAddress() {
        return roadAddress;
    }

    public String getJibunAddress() {
        return jibunAddress;
    }

    @Override
    public String toString() {
        return "RequestUpdateAddressDto{" +
            "recipient='" + recipient + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", detailAddress='" + detailAddress + '\'' +
            ", zonecode=" + zonecode +
            ", roadAddress='" + roadAddress + '\'' +
            ", jibunAddress='" + jibunAddress + '\'' +
            '}';
    }
}
