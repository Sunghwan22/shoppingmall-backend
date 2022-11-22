package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    Page<Inquiry> findAllByProductId(Long productId, Pageable pageable);

    List<Inquiry> findAllByProductId(Long productId);
}
