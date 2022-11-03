package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  CartItem save(CartItem cartItem);

  List<CartItem> findAllByCartId(Long cartId);
}
