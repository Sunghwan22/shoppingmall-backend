package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.exceptions.ProductNotFound;
import kr.megaptera.shoppingMall.exceptions.UserNotFoundException;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService {
  private final ProductRepository productRepository;
  private final WishRepository wishRepository;
  private final UserRepository userRepository;

  public ProductService(ProductRepository productRepository,
                        WishRepository wishRepository,
                        UserRepository userRepository) {
    this.productRepository = productRepository;
    this.wishRepository = wishRepository;
    this.userRepository = userRepository;
  }

  public Product detail(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(ProductNotFound::new);
  }


  // 어떻게 해줄 것이냐 wishRepository에서 userId를 가지고 있는 친구를 찾아서
  public int checkWishList(Long productId, Long userId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(ProductNotFound::new);

    User user = userRepository.findById(userId)
        .orElseThrow(UserNotFoundException::new);

    Wish foundWish = product.wishUserList().stream().filter(wish -> wish.user().id().equals(userId))
        .findFirst().orElse(null);

//    List<Wish> foundWish = wishRepository.findAllByUser(user);
//
//    foundWish.stream().filter(wish -> wish.product().id().equals(productId))
//        .findFirst().orElse(null);
//
    if(foundWish == null) {
      Wish wish = new Wish(product, user);

      product.wishUserList().add(wish);
      wishRepository.save(wish);
    }

    if(foundWish != null) {
      wishRepository.delete(foundWish);
      product.wishUserList().remove(foundWish);
    }

    return product.wishUserList().size();
  }
}
