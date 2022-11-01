package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class UserServiceTest {
  private UserRepository userRepository;
  private UserService userService;

  @BeforeEach
  public void setup() {
    userRepository = mock(UserRepository.class);
    userService = new UserService(userRepository);
  }
}
