package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CartItemDto;
import kr.megaptera.shoppingMall.dtos.CartItemDtos;
import kr.megaptera.shoppingMall.exceptions.CartNotFoundException;
import kr.megaptera.shoppingMall.models.Cart;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.CartRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetCartItemsService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public GetCartItemsService(
        CartRepository cartRepository,
        CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public CartItemDtos getCartItems(Long userId, Integer page) {
        Cart cart = cartRepository.findByUserId(userId)
            .orElseThrow(CartNotFoundException::new);

        Sort sort = Sort.by("createdAt");
        Pageable pageable = PageRequest.of(page - 1, 20, sort);

        List<CartItemDto> cartItemDtos =
            cartItemRepository.findAllByCartId(cart.getId(), pageable)
                .stream().map(CartItem::toDto).toList();

        int pages = cartItemRepository.findAllByCartId(cart.getId(), pageable).getTotalPages();

        int totalNumber = cartItemRepository.findAllByCartId(cart.getId()).size();

        return new CartItemDtos(cartItemDtos, pages, totalNumber);
    }
}
