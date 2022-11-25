package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.LoginResultDto;
import kr.megaptera.shoppingMall.services.LoginService;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("session")
public class SessionController {
    private final LoginService loginService;

    public SessionController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
        @Validated @RequestBody LoginRequestDto loginRequestDto
    ) {
        return loginService.login(
            loginRequestDto.getIdentifier(),
            loginRequestDto.getPassword());
    }

    @GetMapping("/me")
    public LoginResultDto fetchUser(
        @RequestAttribute("userId") Long userId
    ) {
        return loginService.fetchUser(userId);
    }
}
