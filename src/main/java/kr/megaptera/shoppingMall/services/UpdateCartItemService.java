package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CartItemNotFound;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UpdateCartItemService {
    private final CartItemRepository cartItemRepository;

    public UpdateCartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void updateCartItem(
        Long cartItemId,
        Long addAmount,
        String description,
        Long quantity,
        Long totalPrice) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(CartItemNotFound::new);

        cartItem.addAmount(addAmount);
        cartItem.description(description);
        cartItem.quantity(quantity);
        cartItem.totalPrice(totalPrice);
    }
}
