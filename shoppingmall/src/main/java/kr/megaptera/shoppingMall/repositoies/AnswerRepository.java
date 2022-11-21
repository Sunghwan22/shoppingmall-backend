package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.Answer;
import kr.megaptera.shoppingMall.models.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByInquiryId(Long inquiryId);

    List<Answer> findAllByInquiryId(Long inquiryId);
}
