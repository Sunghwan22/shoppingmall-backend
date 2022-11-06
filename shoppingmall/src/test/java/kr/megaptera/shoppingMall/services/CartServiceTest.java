package kr.megaptera.shoppingMall.services;

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

class CartServiceTest {
  private CartService cartService;
  private CartItemRepository cartItemRepository;
  private ProductRepository productRepository;
  private CartRepository cartRepository;

  @BeforeEach
  void setup() {
    cartItemRepository = mock(CartItemRepository.class);
    cartRepository = mock(CartRepository.class);
    productRepository = mock(ProductRepository.class);
    cartService = new CartService(cartItemRepository, productRepository, cartRepository);
  }

  @Test
  void createCartItem() {
    Long productId = 1L;
    Long userId = 1L;

    Cart cart = new Cart(1L, userId);

    Product product = new Product(1L, 1L, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 5000L, 2L, "상품 설명", 3000L);

    Option option = new Option(1L, productId, 5000L, "옵션명");

    CreateCartItemDto createCartItemDto = new CreateCartItemDto(1L, option);

    given(cartRepository.findByUserId(userId))
        .willReturn(Optional.of(cart));

    given(productRepository.findById(productId))
        .willReturn(Optional.of(product));

    CartItem cartItem  = cartService.createCartItem(productId, userId, createCartItemDto);

    given(cartItemRepository.save(any())).willReturn(cartItem);

    verify(cartItemRepository).save(any(CartItem.class));
  }

  @Test
  void cartItemList() {

  }
}
