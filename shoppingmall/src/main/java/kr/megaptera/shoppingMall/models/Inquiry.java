package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.InquiryDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Inquiry {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long productId;

    private String answerStatus;

    private String content;

    private String userNickName;

    private boolean isSecret;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Inquiry() {
    }

    public Inquiry(
        Long id,
        Long userId,
        Long productId,
        String answerStatus,
        String content,
        String userNickName,
        boolean isSecret
    ) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.answerStatus = answerStatus;
        this.content = content;
        this.userNickName = userNickName;
        this.isSecret = isSecret;
    }

    public Inquiry(
        Long productId,
        Long userId,
        String content,
        String userNickName,
        boolean isSecret,
        String answerStatus) {

        this.productId = productId;
        this.userId = userId;
        this.content = content;
        this.userNickName = userNickName;
        this.isSecret = isSecret;
        this.answerStatus = answerStatus;
    }

    public static Inquiry fake(boolean secret, Long userId) {
        Long productId = 1L;

        if (secret) {
            return new Inquiry(
                1L,
                userId,
                productId,
                "미답변",
                "문의 내용",
                "유저 닉네임",
                true);
        }

        return new Inquiry(
            1L,
            userId,
            productId,
            "미답변",
            "문의 내용",
            "유저 닉네임",
            false);
    }

    public boolean isSecret() {
        return isSecret;
    }

    public Long userId() {
        return userId;
    }

    public InquiryDto toDto() {
        return new InquiryDto(id, userId, productId, answerStatus,
            content, userNickName);
    }

    public InquiryDto toSecretDto() {
        return new InquiryDto(id, userId, productId, answerStatus,
            "🔒비밀글 입니다", userNickName);
    }
}
