package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.WishDtos;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductWishesServiceTest {
    private WishRepository wishRepository;
    private GetProductWishesService getProductWishesService;

    @BeforeEach
    void setup() {
        wishRepository = mock(WishRepository.class);
        getProductWishesService = new GetProductWishesService(wishRepository);
    }

    @Test
    void getProductWishes() {
        Long productId = 1L;
        Long userId = 1L;

        List<Wish> wishes = List.of(
            new Wish(1L, productId, userId),
            new Wish(2L, productId, userId + 1)
        );

        given(wishRepository.findAllByProductId(productId))
            .willReturn(wishes);

        WishDtos wishDtos = getProductWishesService.getProductWishes(productId);

        assertThat(wishDtos.getWishes()).hasSize(2);
    }
}
