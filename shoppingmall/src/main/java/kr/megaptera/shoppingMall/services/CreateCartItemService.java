package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CartItemDto;
import kr.megaptera.shoppingMall.dtos.CreateCartItemDto;
import kr.megaptera.shoppingMall.exceptions.CartNotFoundException;
import kr.megaptera.shoppingMall.exceptions.ProductNotFound;
import kr.megaptera.shoppingMall.models.Cart;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.CartRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateCartItemService {
  private final CartItemRepository cartItemRepository;
  private final ProductRepository productRepository;
  private final CartRepository cartRepository;

  public CreateCartItemService(
      CartItemRepository cartItemRepository,
      ProductRepository productRepository,
      CartRepository cartRepository) {
    this.cartItemRepository = cartItemRepository;
    this.productRepository = productRepository;
    this.cartRepository = cartRepository;
  }

  public CartItemDto create(
      Long productId,
      Long userId,
      CreateCartItemDto createCartItemDto) {
    Product product = productRepository.findById(productId)
        .orElseThrow(ProductNotFound::new);

    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(CartNotFoundException::new);

    ProductImage cartItemImage = product.images().stream().
        filter(productImage -> productImage.getThumbnailImage().equals(true))
        .findFirst().orElse(null);

    CartItem cartItem = product.toCartItem(
        createCartItemDto.getQuantity(),
        createCartItemDto.getOption().getAddAmount(),
        createCartItemDto.getOption().getDescription(),
        cart.getId(),
        cartItemImage);

    cart.addCartItem();

    cartItemRepository.save(cartItem);

    return cartItem.toDto(cartItemImage.toDto());
  }
}
