package kr.megaptera.shoppingMall.dtos;

public class AddressDto {
    private Long zoneCode;
    private String fullAddress;
    private String jibunAddress;
    private String detailAddress;

    public AddressDto() {
    }

    public AddressDto(Long zoneCode, String fullAddress, String jibunAddress) {
        this.zoneCode = zoneCode;
        this.fullAddress = fullAddress;
        this.jibunAddress = jibunAddress;
    }

    public Long getZoneCode() {
        return zoneCode;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getJibunAddress() {
        return jibunAddress;
    }
}
