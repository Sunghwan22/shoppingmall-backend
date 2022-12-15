package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.OrderDto;
import kr.megaptera.shoppingMall.dtos.WriteableReviewProductsDto;
import kr.megaptera.shoppingMall.models.Order;
import kr.megaptera.shoppingMall.repositoies.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetWriteableReviewOrdersService {
    private final OrderRepository orderRepository;
    private Pageable pageable;

    public GetWriteableReviewOrdersService(
        OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    public WriteableReviewProductsDto getOrders(Long userId, Integer page) {
        Sort sort = Sort.by("id");
        pageable = PageRequest.of(page - 1, 5, sort);

        Page<Order> orderList = orderRepository.findAllByUserId(userId, pageable);

        List<OrderDto> noReviewOrderList = orderList.stream()
            .filter(order -> order.state().equals(Order.NO_REVIEW)).toList()
            .stream().map(Order::toDto).toList();

        int pages = orderRepository.findAllByUserId(userId, pageable).getTotalPages();

        return new WriteableReviewProductsDto(noReviewOrderList, pages);
    }
}
