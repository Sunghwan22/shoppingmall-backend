package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.controllers.LoginRequestDto;
import kr.megaptera.shoppingMall.dtos.LoginResultDto;
import kr.megaptera.shoppingMall.exceptions.LoginFailed;
import kr.megaptera.shoppingMall.models.Address;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class LoginServiceTest {
    UserRepository userRepository;
    JwtUtil jwtUtil;
    PasswordEncoder passwordEncoder;
    LoginService loginService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        jwtUtil = new JwtUtil("MYSECRET");
        passwordEncoder = new Argon2PasswordEncoder();
        loginService = new LoginService(userRepository, jwtUtil, passwordEncoder);
    }

    @Test
    void login() {
        User user = User.fake();

        given(userRepository.findByIdentifier("tidls45"))
            .willReturn(Optional.of(user));

        user.changePassword("Tjdghks245@", passwordEncoder);

        LoginResultDto loginResultDto = loginService.login("tidls45", "Tjdghks245@");

        assertThat(loginResultDto.getAccessToken()).isNotNull();
        assertThat(loginResultDto.getAddressDto().getZoneCode()).isEqualTo(44637L);
        assertThat(loginResultDto.getPhoneNumber()).isEqualTo("010-3144-7938");
        assertThat(loginResultDto.getName()).isEqualTo("조성환");
    }

    @Test
    void fetchUser() {
        User user = User.fake();

        given(userRepository.findById(1L))
            .willReturn(Optional.of(user));

        LoginResultDto loginResultDto = loginService.fetchUser(1L);

        assertThat(loginResultDto.getName()).isEqualTo("조성환");
        assertThat(loginResultDto.getPhoneNumber()).isEqualTo("010-3144-7938");
        assertThat(loginResultDto.getAddressDto().getZoneCode()).isEqualTo(44637L);
        assertThat(loginResultDto.getAddressDto().getFullAddress()).isEqualTo("울산광역시 정광로 3번길 20");
        assertThat(loginResultDto.getAddressDto().getJibunAddress()).isEqualTo("울산광역시 남구 무거동 1233-12번지");
        assertThat(loginResultDto.getAddressDto().getDetailAddress()).isEqualTo("2층 왼쪽집");
    }

    @Test
    void fetchUserWithBlankDetailAddress() {
        User user = new User(
            1L, "tidls45", "Tjdghks245@", "조성환",
            new Address(44637L, "울산광역시 정광로 3번길 20", "울산광역시 남구 무거동 1233-12번지", ""),
            "010-3144-7938"
        );

        given(userRepository.findById(1L))
            .willReturn(Optional.of(user));

        LoginResultDto loginResultDto = loginService.fetchUser(1L);

        assertThat(loginResultDto.getName()).isEqualTo("조성환");
        assertThat(loginResultDto.getPhoneNumber()).isEqualTo("010-3144-7938");
        assertThat(loginResultDto.getAddressDto().getZoneCode()).isEqualTo(44637L);
        assertThat(loginResultDto.getAddressDto().getFullAddress()).isEqualTo("울산광역시 정광로 3번길 20");
        assertThat(loginResultDto.getAddressDto().getJibunAddress()).isEqualTo("울산광역시 남구 무거동 1233-12번지");
        assertThat(loginResultDto.getAddressDto().getDetailAddress()).isEqualTo("");
    }

    @Test
    void loginWithIncorrectIdentifier() {
        assertThrows(LoginFailed.class, () -> {
            loginService.login("xxxx", "TJdghks245@");
        });
    }

    @Test
    void loginWithIncorrectPassword() {
        assertThrows(LoginFailed.class, () -> {
            loginService.login("tidls45", "xxx");
        });
    }

    @Test
    void loginFailed() {
        assertThrows(LoginFailed.class, () -> {
            loginService.login("xxxxR", "xxx");
        });
    }
}
