package kr.megaptera.shoppingMall.controllers;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UpdateAddressService updateAddressService;

    public UserController(UpdateAddressService updateAddressService) {
        this.updateAddressService = updateAddressService;
    }

    @PatchMapping("/address")
    public void updateAddress(
        @RequestBody RequestUpdateAddressDto requestUpdateAddressDto,
        @RequestAttribute("userId") Long userId
    ) {

        updateAddressService.updateAddress(
            requestUpdateAddressDto.getRecipient(),
            requestUpdateAddressDto.getPhoneNumber(),
            requestUpdateAddressDto.getZonecode(),
            requestUpdateAddressDto.getRoadAddress(),
            requestUpdateAddressDto.getJibunAddress(),
            requestUpdateAddressDto.getDetailAddress(),
            userId
        );
    }
}
