package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.exceptions.ProductNotFound;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product detail(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(ProductNotFound::new);

        List<OptionDto> optionDtos = product.getOptions()
            .stream().map(Option::toDto).toList();
    }

    private List<ProductImageDto> getProductImageDtos(Product product) {
        return product.getProductImages()
            .stream().map(ProductImage::toDto).toList();
    }

    public int createWish(Long productId, Long userId) {
        Product foundProduct = productRepository.findById(productId)
            .orElseThrow(ProductNotFound::new);

        List<Wish> foundWishes = foundProduct.wishes().stream().filter(
            wish -> wish.getUserId().equals(userId)).toList();

        if (foundWishes.size() == 0) {
            Wish wish = new Wish(userId);

            foundProduct.wishes().add(wish);
        }

        if (foundWishes.size() != 0) {
            Wish wish = new Wish(userId);

            foundProduct.wishes().remove(wish);

        }
        return foundProduct.wishes().size();
    }
}
