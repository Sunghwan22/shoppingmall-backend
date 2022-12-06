package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.LoginResultDto;
import kr.megaptera.shoppingMall.dtos.SocialLoginProcessResultDto;
import kr.megaptera.shoppingMall.services.LoginService;
import kr.megaptera.shoppingMall.utils.KakaoAuthUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class KakaoSessionController {
    private final KakaoAuthUtil kakaoAuthUtil;
    private final LoginService loginService;

    public KakaoSessionController(
        KakaoAuthUtil kakaoAuthUtil,
        LoginService loginService) {
        this.kakaoAuthUtil = kakaoAuthUtil;
        this.loginService = loginService;
    }

    @GetMapping("/kakao-token")
    public LoginResultDto login(
        @RequestParam("oauthCode") String oauthCode
    ) {
        SocialLoginProcessResultDto kakaoDto = kakaoAuthUtil.process(oauthCode);

        return loginService.socialLogin(kakaoDto);
    }
}
