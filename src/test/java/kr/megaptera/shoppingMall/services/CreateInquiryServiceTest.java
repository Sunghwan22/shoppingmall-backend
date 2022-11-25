package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CreateInquiryDto;
import kr.megaptera.shoppingMall.dtos.InquiryDto;
import kr.megaptera.shoppingMall.exceptions.InquiryContentBlankException;
import kr.megaptera.shoppingMall.models.Inquiry;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.repositoies.InquiryRepository;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateInquiryServiceTest {
    InquiryRepository inquiryRepository;
    CreateInquiryService createInquiryService;
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        inquiryRepository = mock(InquiryRepository.class);
        userRepository = mock(UserRepository.class);
        createInquiryService = new CreateInquiryService(inquiryRepository, userRepository);
    }

    @Test
    void createInquiry() {
        Long productId = 1L;
        Long userId = 1L;

        CreateInquiryDto createInquiryDto =
            new CreateInquiryDto("상품 문의", true);

        User user = User.fake();

        given(userRepository.findById(userId))
            .willReturn(Optional.of(user));

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

        User user = User.fake();

        given(userRepository.findById(userId))
            .willReturn(Optional.of(user));

        CreateInquiryDto createInquiryDto =
            new CreateInquiryDto("",  true);

        assertThrows(InquiryContentBlankException.class, () -> {
            createInquiryService.createInquiry(productId, userId, createInquiryDto);
        });
    }
}
