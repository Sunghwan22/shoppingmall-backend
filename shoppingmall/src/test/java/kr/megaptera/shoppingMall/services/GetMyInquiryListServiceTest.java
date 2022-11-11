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

class GetMyInquiryListServiceTest {
    private InquiryRepository inquiryRepository;
    private GetMyInquiryListService getMyInquiryListService;

    @BeforeEach
    void setup() {
        inquiryRepository = mock(InquiryRepository.class);
        getMyInquiryListService = new GetMyInquiryListService(inquiryRepository);
    }

    @Test
    void myInquiryList() {
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

        InquiryDtos inquiryDtos =
            getMyInquiryListService.getMyInquiryList(productId, userId, page);

        assertThat(inquiryDtos.getInquiries()).hasSize(2);
        assertThat(inquiryDtos.getInquiries().get(0).getContent()).contains("문의 내용");
    }
}
