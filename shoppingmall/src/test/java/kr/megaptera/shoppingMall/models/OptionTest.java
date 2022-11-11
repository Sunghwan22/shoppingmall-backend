package kr.megaptera.shoppingMall.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionTest {
  @Test
  void equals() {
    Option option= new Option(3000L, "블랙");

    Option otherOption= new Option(3000L, "블랙");

    assertEquals(option, otherOption);
  }
}
