package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CartItemDtos;
import kr.megaptera.shoppingMall.models.Cart;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCartItemsServiceTest {
    CartItemRepository cartItemRepository;
    CartRepository cartRepository;
    GetCartItemsService getCartItemsService;

    @BeforeEach
    void setup() {
        cartItemRepository = mock(CartItemRepository.class);
        cartRepository = mock(CartRepository.class);
        getCartItemsService = new GetCartItemsService(cartRepository, cartItemRepository);
    }

    @Test
    void getCartItems() {
        Long userId = 1L;

        given(cartRepository.findByUserId(userId))
            .willReturn(Optional.of(new Cart(1L, userId)));

        List<CartItem> cartItems = List.of(
            CartItem.fake(),
            CartItem.fake()
        );

        given(cartItemRepository.findAllByCartId(1L))
            .willReturn(cartItems);

        CartItemDtos cartItemDtos = getCartItemsService.getCartItems(
            userId);

        assertThat(cartItemDtos.getCartItems()).hasSize(2);

        assertThat(cartItemDtos.getCartItems().get(0).getName()).isEqualTo("아이폰14");
    }

}