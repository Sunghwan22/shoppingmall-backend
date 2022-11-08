package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.CartRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

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

//  @Test
//  void createCartItem() {
//    Long productId = 1L;
//    Long userId = 1L;
//
//    Cart cart = new Cart(1L, userId);
//
//
//    List<Option> options = List.of(
//        new Option(3000L, "블랙")
//    );
//
//    List<ProductImage> productImages = List.of(
//        new ProductImage("url", true)
//    );
//
//    List<Wish> wishes = List.of(
//        new Wish(1L)
//    );
//
//    Product product = new Product(1L, 1L,productImages, options, wishes, "아이폰14", "애플", "전자기기", 1000L, 120L,
//        1000L, 5000L, 2L, "상품 설명", 3000L);
//
//    CreateCartItemDto createCartItemDto = new CreateCartItemDto(1L, option);
//
//    given(cartRepository.findByUserId(userId))
//        .willReturn(Optional.of(cart));
//
//    given(productRepository.findById(productId))
//        .willReturn(Optional.of(product));
//
//    CartItem cartItem  = cartService.createCartItem(productId, userId, createCartItemDto);
//
//    given(cartItemRepository.save(any())).willReturn(cartItem);
//
//    verify(cartItemRepository).save(any(CartItem.class));
//  }

  @Test
  void cartItemList() {

  }
}
