package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CartItemIdsDto;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteCartItemServiceTest {
    DeleteCartItemService deleteCartItemService;
    CartItemRepository cartItemRepository;

    @BeforeEach
    void setup() {
        cartItemRepository = mock(CartItemRepository.class);
        deleteCartItemService = new DeleteCartItemService(cartItemRepository);
    }

    @Test
    void deleteCartItem() {
        List<Long> cartItemIds = List.of(
            1L, 2L, 3L);

        Long cartItemId = 1L;

        given(cartItemRepository.findById(cartItemId))
            .willReturn(Optional.of(CartItem.fake()));

        deleteCartItemService.deleteCartItem(cartItemIds);

        verify(cartItemRepository).deleteById(1L);
        verify(cartItemRepository).deleteById(2L);
        verify(cartItemRepository).deleteById(3L);
    }

}