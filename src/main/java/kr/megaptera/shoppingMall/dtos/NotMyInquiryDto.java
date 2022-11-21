package kr.megaptera.shoppingMall.dtos;

public class NotMyInquiryDto extends InquiryDto {
    private String content;

    public NotMyInquiryDto(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}
