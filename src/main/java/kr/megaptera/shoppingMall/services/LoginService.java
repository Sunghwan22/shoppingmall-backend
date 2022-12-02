package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.LoginResultDto;
import kr.megaptera.shoppingMall.exceptions.LoginFailed;
import kr.megaptera.shoppingMall.exceptions.UserNotFoundException;
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
        User user = userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

        return new LoginResultDto(
            user.name(), user.phoneNumber(), user.address().toDto()
        );
    }
}
