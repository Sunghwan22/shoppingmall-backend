package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CreateInquiryDto;
import kr.megaptera.shoppingMall.dtos.InquiryDto;
import kr.megaptera.shoppingMall.exceptions.InquiryContentBlankException;
import kr.megaptera.shoppingMall.exceptions.UserNotFoundException;
import kr.megaptera.shoppingMall.models.Inquiry;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.repositoies.InquiryRepository;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateInquiryService {
    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;

    public CreateInquiryService(
        InquiryRepository inquiryRepository,
        UserRepository userRepository) {
        this.inquiryRepository = inquiryRepository;
        this.userRepository = userRepository;
    }

    public InquiryDto createInquiry(
        Long productId,
        Long userId,
        CreateInquiryDto createInquiryDto) {

        User user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            user = userRepository.findBySocialLoginId(String.valueOf(userId))
                .orElseThrow(UserNotFoundException::new);
        }

        if (createInquiryDto.getContent().isBlank()) {
            throw new InquiryContentBlankException();
        }

        Inquiry inquiry = new Inquiry(
            productId,
            userId,
            createInquiryDto.getContent(),
            user.name(),
            createInquiryDto.getIsSecret(),
            "미답변");

        inquiryRepository.save(inquiry);

        return inquiry.toDto();
    }
}
