package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CartItemDto;
import kr.megaptera.shoppingMall.dtos.CartItemDtos;
import kr.megaptera.shoppingMall.exceptions.CartNotFoundException;
import kr.megaptera.shoppingMall.models.Cart;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.CartRepository;
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

    public CartItemDtos getCartItems(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
            .orElseThrow(CartNotFoundException::new);

        List<CartItemDto> cartItemDtos =
            cartItemRepository.findAllByCartId(cart.getId())
                .stream().map(CartItem::toDto).toList();

        int totalNumber = cartItemRepository.findAllByCartId(cart.getId()).size();

        return new CartItemDtos(cartItemDtos, totalNumber);
    }
}
