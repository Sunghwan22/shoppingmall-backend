package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.annotations.MockMvcEncoding;
import kr.megaptera.shoppingMall.dtos.CartItemDetailDto;
import kr.megaptera.shoppingMall.dtos.CartItemDtos;
import kr.megaptera.shoppingMall.dtos.CartItemIdsDto;
import kr.megaptera.shoppingMall.dtos.CreateCartItemDto;
import kr.megaptera.shoppingMall.models.Cart;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.CartRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.services.CreateCartItemService;
import kr.megaptera.shoppingMall.services.DeleteCartItemService;
import kr.megaptera.shoppingMall.services.GetCartItemService;
import kr.megaptera.shoppingMall.services.GetCartItemsService;
import kr.megaptera.shoppingMall.services.UpdateCartItemService;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
@MockMvcEncoding
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private CreateCartItemService createCartItemService;

    @MockBean
    private GetCartItemsService getCartItemsService;

    @MockBean
    private DeleteCartItemService deleteCartItemService;

    @MockBean
    private UpdateCartItemService updateCartItemService;

    @MockBean
    private GetCartItemService getCartItemService;

    @SpyBean
    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {
        Long productId = 1L;
        Long userId = 1L;

        Product product = Product.fake(productId);
        Cart cart = new Cart(1L, userId);

        given(productRepository.findById(productId)).willReturn(Optional.of(product));

        given(cartRepository.findByUserId(userId))
            .willReturn(Optional.of(cart));

        CreateCartItemDto createCartItemDto = new CreateCartItemDto(
            1L, new Option(3000L, "옵션명"), 33000L
        );

        CartItemDtos cartItemDtos = new CartItemDtos(
            List.of(CartItem.fake().toDto(), CartItem.fake().toDto()),
            1
        );

        given(createCartItemService.create(1L, 1L, createCartItemDto))
            .willReturn(CartItem.fake().toDto());

        given(getCartItemsService.getCartItems(1L))
            .willReturn(cartItemDtos);

        given(getCartItemService.getCartItem(1L))
            .willReturn(new CartItemDetailDto(
                CartItem.fake().toDto(),
                Product.fake(1L).options().stream().map(Option::toDto)
                    .toList(),
                30000L
            ));
    }

    @Test
    void createCartItem() throws Exception {
        String accessToken = jwtUtil.encode(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/carts/products/1/cartItems")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"quantity\":\"1\"," +
                    "\"option\":" +
                    "{" +
                    "\"addAmount\":\"3000\"," +
                    "\"description\":\"옵션명\"" +
                    "}," +
                    "\"totalPayment\":\"33000\"" +
                    "}"))
            .andExpect(status().isCreated());

        verify(createCartItemService).create(any(), any(), any());
    }

    @Test
    void CartItemList() throws Exception {
        String accessToken = jwtUtil.encode(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("/carts")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(
                "아이폰14"
            )));

        verify(getCartItemsService).getCartItems(1L);
    }


    @Test
    void deleteCartItem() throws Exception {
        CartItemIdsDto cartItemIdsDto = new CartItemIdsDto(List.of(
            1L, 2L
        ));

        mockMvc.perform(MockMvcRequestBuilders.delete("/carts/cartItems")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"cartItemsId\" : [1,2]" +
                    "}"))
            .andExpect(status().isNoContent());

        verify(deleteCartItemService).deleteCartItem(cartItemIdsDto.getCartItemsId());
    }


    @Test
    void updateCartItem() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/carts/cartItems/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"addAmount\" : \"5000\"," +
                    " \"description\" : \"상품설명\"," +
                    " \"quantity\" : \"3000\"," +
                    " \"totalPrice\": \"330000\"" +
                    "}"))
            .andExpect(status().isNoContent());

        verify(updateCartItemService).updateCartItem(1L, 5000L, "상품설명", 3000L, 330000L);
    }

    @Test
    void detail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/carts/cartItems/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(
                "아이폰14"
            )))
            .andExpect(content().string(containsString(
                "3000"
            )))
            .andExpect(content().string(containsString(
                "상품 설명"
            )));

        verify(getCartItemService).getCartItem(1L);
    }
}
