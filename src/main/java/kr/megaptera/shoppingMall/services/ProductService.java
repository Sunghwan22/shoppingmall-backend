package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.exceptions.ProductNotFound;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.ProductImage;
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

    public ProductDto detail(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(ProductNotFound::new);

        List<OptionDto> optionDtos = product.options()
            .stream().map(Option::toDto).toList();

        List<ProductImageDto> productImageDtos = product.images()
            .stream().map(ProductImage::toDto).toList();

        return product.toDto(optionDtos,productImageDtos);
    }

    private List<ProductImageDto> getProductImageDtos(Product product) {
        return product.images()
            .stream().map(ProductImage::toDto).toList();
    }
}