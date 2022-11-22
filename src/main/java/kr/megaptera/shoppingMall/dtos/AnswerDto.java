package kr.megaptera.shoppingMall.dtos;

public class AnswerDto {
    private Long id;
    private Long inquiryId;
    private String comment;

    public AnswerDto(Long id, Long inquiryId, String comment) {
        this.id = id;
        this.inquiryId = inquiryId;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public Long getInquiryId() {
        return inquiryId;
    }

    public String getComment() {
        return comment;
    }
}
