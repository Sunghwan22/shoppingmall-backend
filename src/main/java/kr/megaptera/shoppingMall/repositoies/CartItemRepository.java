package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  Page<CartItem> findAllByCartId(Long cartId, Pageable pageable);

  List<CartItem> findAllByCartId(Long cartId);
}
