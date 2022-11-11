package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CreateInquiryDto;
import kr.megaptera.shoppingMall.dtos.InquiryDto;
import kr.megaptera.shoppingMall.exceptions.InquiryContentBlankException;
import kr.megaptera.shoppingMall.models.Inquiry;
import kr.megaptera.shoppingMall.repositoies.InquiryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateInquiryServiceTest {
    private InquiryRepository inquiryRepository;
    private CreateInquiryService createInquiryService;

    @BeforeEach
    void setup() {
        inquiryRepository = mock(InquiryRepository.class);
        createInquiryService = new CreateInquiryService(inquiryRepository);
    }

    @Test
    void createInquiry() {
        Long productId = 1L;
        Long userId = 1L;

        CreateInquiryDto createInquiryDto =
            new CreateInquiryDto(userId, productId, "상품 문의", "본인 두두등장", true);

        InquiryDto inquiryDto =
            createInquiryService.createInquiry(productId, userId, createInquiryDto);

        verify(inquiryRepository).save(any(Inquiry.class));

        assertThat(inquiryDto.getUserId()).isEqualTo(userId);
        assertThat(inquiryDto.getContent()).isEqualTo("상품 문의");
        assertThat(inquiryDto.getAnswerStatus()).isEqualTo("미답변");
    }

    @Test
    void createInquiryWithBlankContent() {
        Long productId = 1L;
        Long userId = 1L;

        CreateInquiryDto createInquiryDto =
            new CreateInquiryDto(userId, productId, "", "본인 두두등장", true);

        assertThrows(InquiryContentBlankException.class, () -> {
            createInquiryService.createInquiry(productId, userId, createInquiryDto);
        });
    }
}
