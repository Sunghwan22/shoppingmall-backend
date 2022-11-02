package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class WishServiceTest {
  @Test
  void findByProductId() {
    Long userId = 1L;
    Long productId = 1L;

    WishRepository wishRepository = mock(WishRepository.class);
    WishService wishService = new WishService(wishRepository);

    List<Wish> wishList = List.of(
        new Wish(1L, userId, productId),
        new Wish(1L, userId + 1, productId)
    );

    given(wishRepository.findAllByProductId(productId)).willReturn(wishList);

    List<Wish> foundWishes = wishService.listByProductId(productId);

    assertThat(foundWishes.size()).isEqualTo(2);
  }

  @Test
  void createWishList() {
    Long userId = 1L;
    Long productId = 1L;

    WishRepository wishRepository = mock(WishRepository.class);
    WishService wishService = new WishService(wishRepository);

    List<Wish> wishList = List.of(
        new Wish(1L , userId, productId),
        new Wish(2L , userId + 1, productId)
    );

    given(wishRepository.findAllByProductId(productId)).willReturn(wishList);

    wishService.checkWishList(productId, userId + 2);

    verify(wishRepository).save(any());

  }

  @Test
  void alreadyAddWishList() {
    Long userId = 1L;
    Long productId = 1L;

    WishRepository wishRepository = mock(WishRepository.class);
    WishService wishService = new WishService(wishRepository);

    List<Wish> wishList = List.of(
        new Wish(1L , userId, productId),
        new Wish(2L , userId + 1, productId)
    );

    given(wishRepository.findAllByProductId(productId)).willReturn(wishList);

    wishService.checkWishList(productId, userId);

    verify(wishRepository).delete(any());
  }
}
