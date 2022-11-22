package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.models.Cart;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.CartRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.services.CreateCartItemService;
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

    @MockBean
    private CreateCartItemService createCartItemService;

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
        Long productId = 1L;
        Long userId = 1L;

        String accessToken = jwtUtil.encode(1L);

        Product product = Product.fake(productId);

        given(productRepository.findById(productId)).willReturn(Optional.of(product));

        Cart cart = new Cart(1L, userId);

        given(cartRepository.findByUserId(userId))
            .willReturn(Optional.of(cart));

        mockMvc.perform(MockMvcRequestBuilders.post("/carts/cartItems/products/1")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"quantity\":\"1\"," +
                    "\"option\":" +
                    "{" +
                    "\"addAmount\":\"5000\"," +
                    "\"description\":\"블랙\"" +
                    "}" +
                    "}"))
            .andExpect(status().isCreated());

        verify(createCartItemService).create(any(), any(), any());
    }
}
