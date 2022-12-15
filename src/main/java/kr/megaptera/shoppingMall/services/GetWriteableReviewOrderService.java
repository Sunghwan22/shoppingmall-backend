package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.OrderDto;
import kr.megaptera.shoppingMall.exceptions.OrderNotFoundException;
import kr.megaptera.shoppingMall.models.Order;
import kr.megaptera.shoppingMall.repositoies.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GetWriteableReviewOrderService {
    private final OrderRepository orderRepository;

    public GetWriteableReviewOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto getOrder(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        return order.toDto();
    }
}
