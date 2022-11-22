package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.AnswerDto;
import kr.megaptera.shoppingMall.dtos.InquiryDto;
import kr.megaptera.shoppingMall.dtos.NotMyInquiryDto;
import kr.megaptera.shoppingMall.exceptions.InquiryNotFound;
import kr.megaptera.shoppingMall.models.Answer;
import kr.megaptera.shoppingMall.models.Inquiry;
import kr.megaptera.shoppingMall.repositoies.AnswerRepository;
import kr.megaptera.shoppingMall.repositoies.InquiryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetInquiryDetailService {
    private final InquiryRepository inquiryRepository;
    private final AnswerRepository answerRepository;

    public GetInquiryDetailService(
        InquiryRepository inquiryRepository,
        AnswerRepository answerRepository) {
        this.inquiryRepository = inquiryRepository;
        this.answerRepository = answerRepository;
    }

    public InquiryDto detail(Long inquiryId, Long userId) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
            .orElseThrow(InquiryNotFound::new);

        List<AnswerDto> findAnswerDto = answerRepository.findAllByInquiryId(inquiryId)
            .stream().map(Answer::toDto).toList();

        Long inquiryUserId = inquiry.userId();

        if (userId == null && inquiry.isSecret()) {
            return new NotMyInquiryDto("접근 권한이 없습니다");
        }

        if (userId == null && !inquiry.isSecret()) {
            return inquiry.toDto(findAnswerDto);
        }

        if (userId.equals(inquiryUserId) || !inquiry.isSecret()) {
            return inquiry.toDto(findAnswerDto);
        }

        return new NotMyInquiryDto("접근 권한이 없습니다");
    }
}
