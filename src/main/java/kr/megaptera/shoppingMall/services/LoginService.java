package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.LoginResultDto;
import kr.megaptera.shoppingMall.dtos.SocialLoginProcessResultDto;
import kr.megaptera.shoppingMall.exceptions.LoginFailed;
import kr.megaptera.shoppingMall.exceptions.UserNotFoundException;
import kr.megaptera.shoppingMall.models.Address;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LoginService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginService(
        UserRepository userRepository,
        JwtUtil jwtUtil,
        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResultDto login(String identifier, String password) {
        User user = userRepository.findByIdentifier(identifier)
            .orElseThrow(LoginFailed::new);

        if (!user.authenticate(passwordEncoder, password)) {
            throw new LoginFailed();
        }

        String accessToken = jwtUtil.encode(user.id());

        return new LoginResultDto(
            accessToken, user.name(), user.phoneNumber(), user.address().toDto()
        );
    }

    public LoginResultDto fetchUser(Long userId) {
        System.out.println("유저아이디" + userId);

        User user = userRepository.findBySocialLoginId(String.valueOf(userId))
            .orElse(null);

        if (user == null) {
            user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        }

        return new LoginResultDto(
            user.recipient(), user.phoneNumber(), user.address().toDto()
        );
    }

    public LoginResultDto socialLogin(SocialLoginProcessResultDto kakaoDto) {
        String nickname = kakaoDto.getNickname();
        String email = kakaoDto.getEmail();
        String socialLoginId = kakaoDto.getKakaoUserId();

        User foundUser = userRepository.findBySocialLoginId(socialLoginId)
            .orElse(null);

        if (foundUser == null) {
            String passwordForSocialLogin = socialLoginId;

            User user = new User(
                socialLoginId,
                email,
                passwordForSocialLogin,
                nickname,
                new Address(0L, "", "", ""),
                "",
                User.UNREGISTERED
            );
            user.changePassword(passwordForSocialLogin, passwordEncoder);

            String accessToken = jwtUtil.encode(Long.valueOf(socialLoginId));

            userRepository.save(user);

            return new LoginResultDto(user.id(), accessToken, nickname, user.state());
        }

        String accessToken = jwtUtil.encode(Long.valueOf(socialLoginId));

        return new LoginResultDto(foundUser.id(), accessToken, foundUser.name(),
            foundUser.state());
    }
}
