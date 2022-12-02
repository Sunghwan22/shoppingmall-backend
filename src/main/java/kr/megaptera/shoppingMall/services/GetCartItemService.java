package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CartItemDetailDto;
import kr.megaptera.shoppingMall.dtos.CartItemNotFound;
import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.exceptions.ProductNotFound;
import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetCartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public GetCartItemService(
        CartItemRepository cartItemRepository,
        ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public CartItemDetailDto getCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(CartItemNotFound::new);

        Product product = productRepository.findById(
            cartItem.productId()).orElseThrow(ProductNotFound::new);

        List<OptionDto> optionDtos = product.options().stream()
            .map(Option::toDto).toList();

        return new CartItemDetailDto(cartItem.toDto(), optionDtos, product.price());
    }
}
