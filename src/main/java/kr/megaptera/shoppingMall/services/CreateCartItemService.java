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
    String alternativeImage = "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/NO+IMAGE.gif";

    Product product = productRepository.findById(productId)
        .orElseThrow(ProductNotFound::new);

    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(CartNotFoundException::new);

    String image = product.images().stream().
        filter(productImage -> productImage.getThumbnailImage().equals(true))
        .findFirst().orElse(new ProductImage(alternativeImage, true)).getUrl();  // 이거는 그냥 url만 같고 있게 해야할 것 같다.
                                                                        // 이거는 그냥 이미지만 같고 있게 해야할 것 같다 그러면 가능 할 것 같음// 이미지 객체를가지게 하지 말고 그냥 url만 가지고 있게 해야
    CartItem cartItem = new CartItem(
        createCartItemDto.getQuantity(),
        createCartItemDto.getOption().getAddAmount(),
        createCartItemDto.getOption().getDescription(),
        createCartItemDto.getTotalPayment(),
        product.deliveryFee(),
        product.name(),
        cart.getId(),
        productId,
        image);

    cart.addCartItem();

    cartItemRepository.save(cartItem);

    return cartItem.toDto();
  }
}
