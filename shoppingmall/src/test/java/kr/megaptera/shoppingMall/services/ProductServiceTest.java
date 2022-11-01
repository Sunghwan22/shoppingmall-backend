package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ProductServiceTest {
  private ProductRepository productRepository;
  private ProductService productService;
  private WishRepository wishRepository;
  private UserRepository userRepository;

  @BeforeEach
  public void setup() {
    userRepository = mock(UserRepository.class);
    wishRepository = mock(WishRepository.class);
    productRepository = mock(ProductRepository.class);
    productService = new ProductService(productRepository, wishRepository, userRepository);
  }

  @Test
  void detail() {
    Product product = new Product(1L, 1L, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 100L, 5000L, 2L, "상품 설명");

    given(productRepository.findById(1L)).willReturn(Optional.of(product));

    Product foundProduct = productService.detail(1L);

    assertThat(foundProduct).isNotNull();
    assertThat(foundProduct.maker()).isEqualTo("애플");
  }

  @Test
  void createWishList() {
    Long userId = 1L;
    Long productId = 1L;
// 이미 찜한 상태였으면 -1 이 되야함 근데 1이 왜돠ㅣㅁ?
    Product product = new Product(productId);

    given(productRepository.findById(productId)).willReturn(Optional.of(product));

    User user = new User(userId);

    given(userRepository.findById(userId)).willReturn(Optional.of(user));

    int wishNumber = productService.checkWishList(productId, userId);

    assertThat(wishNumber).isEqualTo(1L);
  }

  @Test
  void alreadyExistWishList() {
    Long userId = 1L;
    Long productId = 1L;

    Product product = new Product(productId);

    given(productRepository.findById(productId)).willReturn(Optional.of(product));

    User user1 = new User(userId);

    given(userRepository.findById(userId)).willReturn(Optional.of(user1));


    User user2 = new User(userId + 1);

    given(userRepository.findById(userId)).willReturn(Optional.of(user2));

    User user3 = new User(userId + 2);

    given(userRepository.findById(userId)).willReturn(Optional.of(user3));


    List<Wish> wishUserList = product.wishUserList();

    wishUserList.add(new Wish(product, user1));
    wishUserList.add(new Wish(product, user2));
    wishUserList.add(new Wish(product, user3));

    int wishNumber = productService.checkWishList(productId, userId);

    assertThat(wishNumber).isEqualTo(2L);
  }
}
