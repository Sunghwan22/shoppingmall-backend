package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.InquiryDtos;
import kr.megaptera.shoppingMall.models.Inquiry;
import kr.megaptera.shoppingMall.repositoies.InquiryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetInquiryListServiceTest {
    private GetInquiryListService getInquiryListService;
    private InquiryRepository inquiryRepository;

    @BeforeEach
    void setup() {
        inquiryRepository = mock(InquiryRepository.class);
        getInquiryListService = new GetInquiryListService(inquiryRepository);
    }

    @Test
    void getInquiryListWithLogin() {
        Long userId = 1L;
        Long productId = 1L;

        List<Inquiry> inquiries = List.of(
            Inquiry.fake(true, userId),
            Inquiry.fake(true, userId + 1L),
            Inquiry.fake(false, userId)
        );

        int page = 1;
        Sort sort = Sort.by("createdAt");
        Pageable pageable = PageRequest.of(page - 1, 5, sort);

        given(inquiryRepository.findAllByProductId(productId, pageable))
            .willReturn(new PageImpl<>(inquiries, pageable, 1));

        InquiryDtos inquiryDtos = getInquiryListService.getInquiryList(productId, userId, page);

        assertThat(inquiryDtos.getInquiries()).hasSize(3);
        assertThat(inquiryDtos.getInquiries().get(0).getContent())
            .contains("문의 내용");
        assertThat(inquiryDtos.getInquiries().get(1).getContent())
            .contains("비밀글 입니다");
        assertThat(inquiryDtos.getInquiries().get(2).getContent())
            .contains("문의 내용");
    }

    @Test
    void getInquiryListWithOutLogin() {
        Long userId = null;
        Long productId = 1L;

        List<Inquiry> inquiries = List.of(
            Inquiry.fake(true, 1L),
            Inquiry.fake(true, 2L),
            Inquiry.fake(false, 1L)
        );

        int page = 1;
        Sort sort = Sort.by("createdAt");
        Pageable pageable = PageRequest.of(page - 1, 5, sort);

        given(inquiryRepository.findAllByProductId(productId, pageable))
            .willReturn(new PageImpl<>(inquiries, pageable, 1));

        InquiryDtos inquiryDtos = getInquiryListService.getInquiryList(productId, userId, page);

        assertThat(inquiryDtos.getInquiries()).hasSize(3);
        assertThat(inquiryDtos.getInquiries().get(0).getContent())
            .contains("🔒비밀글 입니다");
        assertThat(inquiryDtos.getInquiries().get(1).getContent())
            .contains("🔒비밀글 입니다");
        assertThat(inquiryDtos.getInquiries().get(2).getContent())
            .contains("문의 내용");
    }
}
