package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import kr.megaptera.shoppingMall.services.CreateWishService;
import kr.megaptera.shoppingMall.services.GetProductWishesService;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WishController.class)
class WishControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateWishService createWishService;

    @MockBean
    private WishRepository wishRepository;

    @MockBean
    private GetProductWishesService getProductWishesService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void createWishes() throws Exception {
        Long productId = 1L;
        Long userId = 1L;

        String accessToken = jwtUtil.encode(1L);

        List<Wish> wishes = List.of();

        given(wishRepository.findAllByProductId(productId))
            .willReturn(wishes);

        mockMvc.perform(MockMvcRequestBuilders.post("/wishes/products/1")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

        verify(createWishService).create(productId, userId);
    }

    @Test
    void alreadyExistWishes() throws Exception {
        Long productId = 1L;
        Long userId = 1L;

        String accessToken = jwtUtil.encode(1L);

        List<Wish> wishes = List.of(
            new Wish(1L, productId, userId)
        );

        given(wishRepository.findAllByProductId(productId))
            .willReturn(wishes);

        mockMvc.perform(MockMvcRequestBuilders.post("/wishes/products/1")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().string(containsString(
                ""
            )));

        verify(createWishService).create(productId, userId);
    }

    @Test
    void getProductWishes() throws Exception {
        Long productId = 1L;
        Long userId = 1L;

        List<Wish> wishes = List.of(
            new Wish(1L, productId, userId),
            new Wish(2L, productId, userId + 1)
        );

        given(wishRepository.findAllByProductId(productId))
            .willReturn(wishes);

        mockMvc.perform(MockMvcRequestBuilders.get("/wishes/products/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(
                "")));

        verify(getProductWishesService).getProductWishes(1L);
    }
}
