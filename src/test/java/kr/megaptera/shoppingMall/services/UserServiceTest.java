package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserServiceTest {
  private UserRepository userRepository;
  private UserService userService;

  @BeforeEach
  public void setup() {
    userRepository = mock(UserRepository.class);
    userService = new UserService(userRepository);
  }

  @Test
  void findById() {
    User user = new User(1L, "tidls45", "tjdghks245", "조성환", "스을특별시");

    given(userRepository.findById(1L)).willReturn(Optional.of(user));

    User foundUser = userService.findById(1L);

    assertThat(foundUser.id()).isEqualTo(1L);
    assertThat(foundUser.identifier()).isEqualTo("tidls45");
    assertThat(foundUser.name()).isEqualTo("조성환");
  }
}
