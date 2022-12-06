package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.AddressDto;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {
    private Long zoneCode;

    private String fullAddress;

    private String jibunAddress;

    private String detailAddress;

    public Address() {
    }

    public Address(
        Long zoneCode,
        String fullAddress,
        String jibunAddress,
        String detailAddress) {
        this.zoneCode = zoneCode;
        this.fullAddress = fullAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
    }

    public AddressDto toDto() {
        return new AddressDto(zoneCode, fullAddress, jibunAddress, detailAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(zoneCode, address.zoneCode) && Objects.equals(fullAddress, address.fullAddress) && Objects.equals(jibunAddress, address.jibunAddress) && Objects.equals(detailAddress, address.detailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneCode, fullAddress, jibunAddress, detailAddress);
    }

    @Override
    public String toString() {
        return "Address{" +
            "zoneCode=" + zoneCode +
            ", fullAddress='" + fullAddress + '\'' +
            ", jibunAddress='" + jibunAddress + '\'' +
            ", detailAddress='" + detailAddress + '\'' +
            '}';
    }

    public Long zoneCode() {
        return zoneCode;
    }

    public String fullAddress() {
        return fullAddress;
    }

    public String jibunAddress() {
        return jibunAddress;
    }

    public String detailAddress() {
        return detailAddress;
    }

    public void setZoneCode(Long zoneCode) {
        this.zoneCode = zoneCode;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public void setJibunAddress(String jibunAddress) {
        this.jibunAddress = jibunAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
}
