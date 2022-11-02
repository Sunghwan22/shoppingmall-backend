package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CreateCartItemDto;
import kr.megaptera.shoppingMall.exceptions.ProductNotFound;
import kr.megaptera.shoppingMall.exceptions.UserNotFoundException;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class CartService {
  private ProductRepository productRepository;
  private UserRepository userRepository;

  public CartItem createCartItem(Long productId, Long userId, CreateCartItemDto createCartItemDto) {
    Product product = productRepository.findById(productId)
        .orElseThrow(ProductNotFound::new);

    User user = userRepository.findById(userId)
        .orElseThrow(UserNotFoundException::new);

    product.toCartItem(createCartItemDto.getQuantity(), createCartItemDto.getOption());

    return CartItem;
  }
}
