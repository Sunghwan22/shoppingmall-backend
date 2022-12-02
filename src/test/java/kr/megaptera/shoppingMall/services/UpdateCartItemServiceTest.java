package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateCartItemServiceTest {
    UpdateCartItemService updateCartItemService;
    CartItemRepository cartItemRepository;

    @BeforeEach
    void setup() {
        cartItemRepository = mock(CartItemRepository.class);
        updateCartItemService = new UpdateCartItemService(cartItemRepository);
    }

    @Test
    void update() {
        Long cartItemId = 1L;

        CartItem cartItem = CartItem.fake();

        given(cartItemRepository.findById(cartItemId))
            .willReturn(Optional.of(cartItem));

        updateCartItemService.updateCartItem(cartItemId, 5000L, "상품설명입니다", 3L, 500000L);

        assertThat(cartItem.getQuantity()).isEqualTo(3L);
        assertThat(cartItem.getAddAmount()).isEqualTo(5000L);
        assertThat(cartItem.getDescription()).isEqualTo("상품설명입니다");
        assertThat(cartItem.getTotalPrice()).isEqualTo(500000L);
    }
}