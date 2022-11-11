package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CreateInquiryDto;
import kr.megaptera.shoppingMall.dtos.InquiryDto;
import kr.megaptera.shoppingMall.exceptions.InquiryContentBlankException;
import kr.megaptera.shoppingMall.models.Inquiry;
import kr.megaptera.shoppingMall.repositoies.InquiryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateInquiryService {
    private InquiryRepository inquiryRepository;

    public CreateInquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    public InquiryDto createInquiry(
        Long productId,
        Long userId,
        CreateInquiryDto createInquiryDto) {

        if(createInquiryDto.getContent().isBlank()) {
            throw new InquiryContentBlankException();
        }

        Inquiry inquiry = new Inquiry(
            productId,
            userId,
            createInquiryDto.getContent(),
            createInquiryDto.getUserNickName(),
            createInquiryDto.getIsSecret(),
            "미답변");

        inquiryRepository.save(inquiry);

        return inquiry.toDto();
    }
}
