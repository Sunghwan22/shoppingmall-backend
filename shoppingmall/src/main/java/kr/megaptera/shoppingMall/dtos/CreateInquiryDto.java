package kr.megaptera.shoppingMall.dtos;

import javax.validation.constraints.NotBlank;

public class CreateInquiryDto {
    private Long userId;

    private Long productId;

    private String content;

    private String userNickName;

    private boolean isSecret;

    public CreateInquiryDto(
        Long userId,
        Long productId,
        String content,
        String userNickName,
        boolean isSecret) {
        this.userId = userId;
        this.productId = productId;
        this.content = content;
        this.userNickName = userNickName;
        this.isSecret = isSecret;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getContent() {
        return content;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public boolean getIsSecret() {
        return isSecret;
    }
}
