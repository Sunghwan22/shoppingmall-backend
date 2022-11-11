package kr.megaptera.shoppingMall.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InquiryDto {
    private Long id;

    private Long userId;

    private Long productId;

    private String answerStatus;

    private String content;

    private boolean isSecret;

    private String userNickName;

    public InquiryDto(Long id,
                      Long userId,
                      Long productId,
                      String answerStatus,
                      String content,
                      String userNickName) {

        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.answerStatus = answerStatus;
        this.content = content;
        this.userNickName = userNickName;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getAnswerStatus() {
        return answerStatus;
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

    public String getCreatedAt() {
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return now.format(formatter);
    }
}
