package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CreateCartItemDto;
import kr.megaptera.shoppingMall.exceptions.CartNotFoundException;
import kr.megaptera.shoppingMall.exceptions.ProductNotFound;
import kr.megaptera.shoppingMall.models.Cart;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.CartRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CartService {
  private final CartItemRepository cartItemRepository;
  private final ProductRepository productRepository;
  private final CartRepository cartRepository;

  public CartService(CartItemRepository cartItemRepository,
                     ProductRepository productRepository,
                     CartRepository cartRepository) {
    this.cartItemRepository = cartItemRepository;
    this.productRepository = productRepository;
    this.cartRepository = cartRepository;
  }

  public CartItem createCartItem(Long productId, Long userId, CreateCartItemDto createCartItemDto) {
    Product product = productRepository.findById(productId)
        .orElseThrow(ProductNotFound::new);

    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(CartNotFoundException::new);

    CartItem cartItem = product.toCartItem(
        createCartItemDto.getQuantity(),
        createCartItemDto.getOption().getAddAmount(),
        createCartItemDto.getOption().getDescription(),
        cart.getId());

    cart.addCartItem();

    return cartItemRepository.save(cartItem);
  }

  public List<CartItem> list(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(CartNotFoundException::new);

    return cartItemRepository.findAllByCartId(cart.getId());
  }
}
