package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CartItemDto;
import kr.megaptera.shoppingMall.dtos.CreateCartItemDto;
import kr.megaptera.shoppingMall.models.Cart;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.CartRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCartItemServiceTest {
    private CartItemRepository cartItemRepository;
    private CreateCartItemService createCartItemService;
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        cartItemRepository = mock(CartItemRepository.class);
        cartRepository = mock(CartRepository.class);
        productRepository = mock(ProductRepository.class);
        createCartItemService =
            new CreateCartItemService(cartItemRepository, productRepository, cartRepository);
    }

    @Test
    void create() {
        Long productId = 1L;
        Long userId = 1L;

        Cart cart = mock(Cart.class);
        Product product = Product.fake(productId);

        given(productRepository.findById(productId)).willReturn(Optional.of(product));

        given(cartRepository.findByUserId(userId)).willReturn(Optional.of(cart));

        CreateCartItemDto createCartItemDto = new CreateCartItemDto(
            1L, new Option(3000L, "옵션명")
        );

        CartItemDto cartItemDto =
            createCartItemService.create(productId, userId, createCartItemDto);

        assertThat(cartItemDto).isNotNull();
        assertThat(cartItemDto.getCartItemPrice()).isEqualTo(4000);
        assertThat(cartItemDto.getThumbNailImage().getUrl()).isEqualTo("url");
        assertThat(cartItemDto.getThumbNailImage().getThumbnailImage()).isEqualTo(true);

        verify(cart).addCartItem();
        verify(cartItemRepository).save(any(CartItem.class));
    }

}