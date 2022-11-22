package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.AnswerDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    private Long inquiryId;

    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Answer() {
    }

    public Answer(Long id, Long inquiryId, String comment) {
        this.id = id;
        this.inquiryId = inquiryId;
        this.comment = comment;
    }

    public Answer(Long inquiryId, String comment) {
        this.inquiryId = inquiryId;
        this.comment = comment;
    }

    public AnswerDto toDto() {
        return new AnswerDto(id, inquiryId, comment);
    }
}
