package kr.megaptera.shoppingMall.dtos;

public class SocialLoginProcessResultDto {
    private String accessToken;
    private String refreshToken;
    private String nickname;
    private String email;
    private String kakaoUserId;

    public SocialLoginProcessResultDto() {
    }


    public SocialLoginProcessResultDto(
        String accessToken,
        String refreshToken,
        String nickname,
        String email,
        String kakaoUserId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.nickname = nickname;
        this.email = email;
        this.kakaoUserId = kakaoUserId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getKakaoUserId() {
        return kakaoUserId;
    }
}
