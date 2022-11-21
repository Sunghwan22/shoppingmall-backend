package kr.megaptera.shoppingMall.dtos;

import javax.validation.constraints.NotBlank;

public class CreateInquiryDto {
    private String content;

    private boolean isSecret;

    private CreateInquiryDto() {
    }

    public CreateInquiryDto(
        String content,
        boolean isSecret) {
        this.content = content;
        this.isSecret = isSecret;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsSecret() {
        return isSecret;
    }
}
