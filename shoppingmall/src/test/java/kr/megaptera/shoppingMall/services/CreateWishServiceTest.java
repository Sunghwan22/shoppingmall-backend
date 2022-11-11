package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.WishDto;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateWishServiceTest {
    private CreateWishService createWishService;
    private WishRepository wishRepository;

    @BeforeEach
    void setup() {
        wishRepository = mock(WishRepository.class);
        createWishService = new CreateWishService(wishRepository);
    }

    @Test
    void create() {
        Long userId = 1L;
        Long productId = 1L;

        List<Wish> foundWishes = List.of(
            new Wish(1L, productId + 1, userId + 1),
            new Wish(1L, productId + 2, userId + 2)
        );

        given(wishRepository.findAllByProductId(productId)).willReturn(foundWishes);

        List<WishDto> wishDtos = createWishService.create(userId, productId);

        verify(wishRepository).save(any(Wish.class));
    }

    @Test
    void alreadyExistWish() {
        Long userId = 1L;
        Long productId = 1L;

        List<Wish> foundWishes = List.of(
            new Wish(1L, productId, userId),
            new Wish(1L, productId + 1, userId + 1)
        );

        given(wishRepository.findAllByProductId(productId)).willReturn(foundWishes);

        List<WishDto> wishDtos = createWishService.create(userId, productId);

        verify(wishRepository).delete(any(Wish.class));
    }
}
