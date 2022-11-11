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
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GetInquiryListService {
    private final InquiryRepository inquiryRepository;

    public GetInquiryListService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    public InquiryDtos getInquiryList(Long productId, Long userId, Integer page) {
        Sort sort = Sort.by("createdAt");
        Pageable pageable = PageRequest.of(page - 1, 5, sort);

        Page<Inquiry> inquiryList = inquiryRepository.findAllByProductId(productId, pageable);

        List<InquiryDto> filteredInquiryList = new ArrayList<>();

        if (userId != null) {
            filteredInquiryList.addAll(inquiryList.stream().filter(
                    inquiry -> inquiry.isSecret() && inquiry.userId().equals(userId))
                .toList().stream().map(Inquiry::toDto).toList());
        }

        filteredInquiryList.addAll(inquiryList.stream().filter(
                inquiry -> inquiry.isSecret() && !inquiry.userId().equals(userId))
            .toList().stream().map(Inquiry::toSecretDto).toList());

        filteredInquiryList.addAll(inquiryList.stream().filter(
            inquiry -> !inquiry.isSecret()).toList().stream().map(
            Inquiry::toDto).toList());

        int totalInquiryNumber = inquiryRepository.findAllByProductId(productId).size();

        return new InquiryDtos(filteredInquiryList, page, totalInquiryNumber);
    }
}
