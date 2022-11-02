package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
  List<Wish> findAllByProductId(Long productId);

  List<Wish> findAllByUserId(Long userId);
}
