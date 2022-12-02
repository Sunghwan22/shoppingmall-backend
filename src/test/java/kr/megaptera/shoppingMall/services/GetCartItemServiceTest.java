package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CartItemDetailDto;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCartItemServiceTest {
    GetCartItemService getCartItemService;
    CartItemRepository cartItemRepository;
    ProductRepository productRepository;

    @BeforeEach
    void setup() {
        cartItemRepository = mock(CartItemRepository.class);
        productRepository = mock(ProductRepository.class);
        getCartItemService = new GetCartItemService(
            cartItemRepository, productRepository
        );
    }

    @Test
    void detailCartItem() {
        Long productId = 1L;
        Long cartItemId = 1L;

        Product product = Product.fake(productId);
        CartItem cartItem = CartItem.fake();

        given(productRepository.findById(productId))
            .willReturn(Optional.of(product));

        given(cartItemRepository.findById(cartItemId))
            .willReturn(Optional.of(cartItem));

        CartItemDetailDto cartItemDetailDto =
            getCartItemService.getCartItem(cartItemId);

        assertThat(cartItemDetailDto.getCartItem().getName())
            .isEqualTo("아이폰14");
        assertThat(cartItemDetailDto.getCartItem().getDeliveryFee())
            .isEqualTo(3000L);

        assertThat(cartItemDetailDto.getOptions().get(0).getAddAmount())
            .isEqualTo(3000L);
        assertThat(cartItemDetailDto.getOptions().get(0).getDescription())
            .isEqualTo("상품 설명");
    }
}
