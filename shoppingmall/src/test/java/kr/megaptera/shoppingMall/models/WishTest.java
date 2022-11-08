package kr.megaptera.shoppingMall.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WishTest {
  @Test
  void equals() {
    Wish wish = new Wish(1L);
    Wish otherWish = new Wish(1L);

    assertEquals(wish, otherWish);
  }
}
