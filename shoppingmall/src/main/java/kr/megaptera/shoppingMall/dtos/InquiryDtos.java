package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class InquiryDtos {
    private List<InquiryDto> inquiries;

    private Integer page;

    private int totalInquiryNumber;

    public InquiryDtos(List<InquiryDto> inquiries, Integer page, int totalInquiryNumber) {
        this.inquiries = inquiries;
        this.page = page;
        this.totalInquiryNumber = totalInquiryNumber;
    }

    public InquiryDtos(List<InquiryDto> myInquiryList, Integer page) {
        this.inquiries = myInquiryList;
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    public int getTotalInquiryNumber() {
        return totalInquiryNumber;
    }

    public List<InquiryDto> getInquiries() {
        return inquiries;
    }
}
