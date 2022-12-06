package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.exceptions.UserNotFoundException;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UpdateAddressService {
    private final UserRepository userRepository;

    public UpdateAddressService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateAddress(
        String recipient,
        String phoneNumber,
        Long zoneCode,
        String fullAddress,
        String jibunAddress,
        String detailAddress,
        Long userId) {

        User user = userRepository.findBySocialLoginId(String.valueOf(userId))
            .orElse(null);

        if(user == null) {
            user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        }

        System.out.println("유저주소" + user.address());

        user.setRecipient(recipient);
        user.setPhoneNumber(phoneNumber);
        user.address().setZoneCode(zoneCode);
        user.address().setFullAddress(fullAddress);
        user.address().setJibunAddress(jibunAddress);
        user.address().setDetailAddress(detailAddress);
    }
}
