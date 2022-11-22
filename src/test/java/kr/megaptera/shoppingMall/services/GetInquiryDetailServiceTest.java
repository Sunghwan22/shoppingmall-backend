package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.InquiryDto;
import kr.megaptera.shoppingMall.models.Inquiry;
import kr.megaptera.shoppingMall.repositoies.AnswerRepository;
import kr.megaptera.shoppingMall.repositoies.InquiryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetInquiryDetailServiceTest {
    InquiryRepository inquiryRepository;
    GetInquiryDetailService getInquiryDetailService;
    AnswerRepository answerRepository;

    @BeforeEach
    void setup() {
        inquiryRepository = mock(InquiryRepository.class);
        answerRepository = mock(AnswerRepository.class);
        getInquiryDetailService =
            new GetInquiryDetailService(inquiryRepository, answerRepository);
    }

    @Test
    void detail() {
        Inquiry inquiry = Inquiry.fake(true, 1L);

        given(inquiryRepository.findById(1L))
            .willReturn(Optional.of(inquiry));

        InquiryDto inquiryDto = getInquiryDetailService.detail(1L, 1L);

        assertThat(inquiryDto.getContent()).isEqualTo("문의 내용");
        assertThat(inquiryDto.getUserId()).isEqualTo(1L);
    }

    @Test
    void secretInquiryDetailWithOther() {
        Inquiry inquiry = Inquiry.fake(true, 1L);

        given(inquiryRepository.findById(1L))
            .willReturn(Optional.of(inquiry));

        InquiryDto inquiryDto = getInquiryDetailService.detail(1L, 10L);

        assertThat(inquiryDto.getContent()).isEqualTo("접근 권한이 없습니다");

        Inquiry otherInquiry = Inquiry.fake(true, 5L);

        given(inquiryRepository.findById(5L))
            .willReturn(Optional.of(otherInquiry));

        InquiryDto otherInquiryDto = getInquiryDetailService.detail(5L, 10L);

        assertThat(otherInquiryDto.getContent()).isEqualTo("접근 권한이 없습니다");
    }

    @Test
    void notSecretInquiry() {
        Inquiry inquiry = Inquiry.fake(false, 1L);

        given(inquiryRepository.findById(1L))
            .willReturn(Optional.of(inquiry));

        InquiryDto inquiryDto = getInquiryDetailService.detail(1L, 10L);

        assertThat(inquiryDto.getContent()).isEqualTo("문의 내용");
    }
}
