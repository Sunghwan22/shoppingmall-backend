package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteWishItemServiceTest {
    WishRepository wishRepository;
    DeleteWishItemService deleteWishItemService;

    @BeforeEach
    void setUp() {
        wishRepository = mock(WishRepository.class);
        deleteWishItemService = new DeleteWishItemService(wishRepository);
    }

    @Test
    void deleteWishItem() {
        Long userId = 1L;
        Long productId = 1L;

        List<Wish> wishList = List.of(
            new Wish(productId, userId),
            new Wish(productId + 1, userId)
        );

        given(wishRepository.findAllByUserId(userId))
            .willReturn(wishList);

        deleteWishItemService.deleteWishItem(productId, userId);

        verify(wishRepository).deleteByProductId(productId);
    }

}