package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class WishServiceTest {
  @Test
  void wishList() {
    User user1 = new User(1L, "tidls45", "tjdghks245", "조성환", "스울특별시");
    User user2 = new User(2L, "tidls3144", "tjdghks245", "안녕하세요", "스울특별시");


    Product product1 = new Product(1L, 1L, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 100L, 5000L, 2L, "상품 설명");

    Product product2 = new Product(2L, 1L, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 100L, 5000L, 2L, "상품 설명");

    List<Wish> wishList = List.of(
        new Wish(2L, user1, product1),
        new Wish(1L, user2, product2)
    );


    WishRepository wishRepository = mock(WishRepository.class);

    WishService wishService = new WishService(wishRepository);

    given(wishService.list(1L)).willReturn(wishList);

    List<Wish> foundWishList = wishService.list(1L);

    assertThat(foundWishList).hasSize(2);
  }
}
