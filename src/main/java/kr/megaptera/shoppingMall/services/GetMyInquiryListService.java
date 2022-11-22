package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.InquiryDto;
import kr.megaptera.shoppingMall.dtos.InquiryDtos;
import kr.megaptera.shoppingMall.models.Inquiry;
import kr.megaptera.shoppingMall.repositoies.InquiryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetMyInquiryListService {
    private InquiryRepository inquiryRepository;

    public GetMyInquiryListService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    public InquiryDtos getMyInquiryList(Long productId, Long userId, Integer page) {
        Sort sort = Sort.by("createdAt");
        Pageable pageable = PageRequest.of(page - 1, 5, sort);

        Page<Inquiry> inquiryDtosByProductId =
            inquiryRepository.findAllByProductId(productId, pageable);

        List<InquiryDto> myInquiryList = inquiryDtosByProductId.stream().
            filter(inquiry -> inquiry.userId().equals(userId))
            .map(Inquiry::toDto).toList();

        return new InquiryDtos(myInquiryList, page);
    }
}
