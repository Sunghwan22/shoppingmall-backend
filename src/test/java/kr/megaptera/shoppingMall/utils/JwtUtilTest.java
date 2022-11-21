package kr.megaptera.shoppingMall.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {
  static final String SECRET = "SECRET";

  private JwtUtil jwtUtil;

  @BeforeEach
  void setUp() {
    jwtUtil = new JwtUtil(SECRET);
  }

  @Test
  void encodeAndDecode() {
    Long original = 1L;

    String token = jwtUtil.encode(original);

    assertThat(token).contains(".");

    Long userId = jwtUtil.decode(token);

    assertThat(userId).isEqualTo(1L);
  }
}
