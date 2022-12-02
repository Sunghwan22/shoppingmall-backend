package kr.megaptera.shoppingMall.repositoies;

import kr.megaptera.shoppingMall.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
