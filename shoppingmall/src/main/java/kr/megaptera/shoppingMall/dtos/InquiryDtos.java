package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class InquiryDtos {
    private List<InquiryDto> inquiries;

    private Integer pages;

    private int totalInquiryNumber;

    public InquiryDtos(List<InquiryDto> inquiries, Integer pages, int totalInquiryNumber) {
        this.inquiries = inquiries;
        this.pages = pages;
        this.totalInquiryNumber = totalInquiryNumber;
    }

    public InquiryDtos(List<InquiryDto> myInquiryList, Integer pages) {
        this.inquiries = myInquiryList;
        this.pages = pages;
    }

    public Integer getPages() {
        return pages;
    }

    public int getTotalInquiryNumber() {
        return totalInquiryNumber;
    }

    public List<InquiryDto> getInquiries() {
        return inquiries;
    }
}
