package kr.megaptera.shoppingMall.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductImageTest {
  @Test
  void equals() {
    ProductImage productImage = new ProductImage("url", true);

    ProductImage otherProductImage = new ProductImage("url", true);

    assertEquals(productImage, otherProductImage);
  }
}