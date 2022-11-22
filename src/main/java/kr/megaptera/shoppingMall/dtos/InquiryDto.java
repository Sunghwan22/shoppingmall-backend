package kr.megaptera.shoppingMall.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InquiryDto {
    private Long id;

    private Long userId;

    private Long productId;

    private String answerStatus;

    private String content;

    private boolean isSecret;

    private List<AnswerDto> findAnswerDto;

    private String userNickName;

    public InquiryDto(Long id,
                      Long userId,
                      Long productId,
                      String answerStatus,
                      String content,
                      String userNickName,
                      boolean isSecret) {

        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.answerStatus = answerStatus;
        this.content = content;
        this.userNickName = userNickName;
        this.isSecret = isSecret;
    }

    public InquiryDto(
        Long id,
        Long userId,
        Long productId,
        String answerStatus,
        String content,
        String userNickName,
        boolean isSecret,
        List<AnswerDto> findAnswerDto) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.answerStatus = answerStatus;
        this.content = content;
        this.userNickName = userNickName;
        this.isSecret = isSecret;
        this.findAnswerDto = findAnswerDto;
    }

    public InquiryDto() {

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

    public List<AnswerDto> getAnswers() {
        return findAnswerDto;
    }

    public String getCreatedAt() {
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return now.format(formatter);
    }
}
