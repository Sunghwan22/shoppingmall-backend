package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.models.Cart;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.CartRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.services.CartService;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private CartService cartService;

  @MockBean
  private CartRepository cartRepository;

  @MockBean
  private ProductRepository productRepository;

  @MockBean
  private CartItemRepository cartItemRepository;

  @SpyBean
  private JwtUtil jwtUtil;

  @Test
  void createCartItem() throws Exception {
    String accessToken = jwtUtil.encode(1L);

    Product product = new Product(1L, 1L, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 5000L, 2L, "상품 설명", 3000L);

    given(productRepository.findById(1L)).willReturn(Optional.of(product));

    Cart cart = new Cart(1L, 1L, 1L);

    given(cartRepository.findByUserId(1L))
        .willReturn(Optional.of(cart));

    mockMvc.perform(MockMvcRequestBuilders.post("/cart/product/1")
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"quantity\":\"1\"," +
                "\"option\":" +
                "{" +
                "\"id\":\"1\"," +
                "\"productId\":\"1\"," +
                "\"addAmount\":\"5000\"," +
                "\"description\":\"블랙\"" +
                "}" +
                "}"))
        .andExpect(status().isCreated());

    verify(cartItemRepository).save(any(CartItem.class));
    verify(cartService).createCartItem(any(), any(), any());
  }
}
