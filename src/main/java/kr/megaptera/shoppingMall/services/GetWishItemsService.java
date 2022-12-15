package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.exceptions.ProductNotFound;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GetWishItemsService {
    private final ProductRepository productRepository;
    private final WishRepository wishRepository;

    public GetWishItemsService(
        ProductRepository productRepository,
        WishRepository wishRepository) {
        this.productRepository = productRepository;
        this.wishRepository = wishRepository;
    }

    public List<ProductDto> getWishItems(Long userId) {
        String alternativeImage = "https://test-s3-image.s3.ap-no" +
            "rtheast-2.amazonaws.com/NO+IMAGE.gif";

        List<Wish> wishList = wishRepository.findAllByUserId(userId);

        List<ProductDto> productDtos= new ArrayList<>();

        for (Wish wish : wishList) {
            Product product = productRepository.findById(
                wish.productId()).orElseThrow(ProductNotFound::new);

            List<OptionDto> optionDtos = product.options()
                .stream().map(Option::toDto).toList();

            if (product.images().size() == 0 || product.images().size() == 0) {
                product.images().add(new ProductImage(
                    alternativeImage, true
                ));

                product.images().add(new ProductImage(
                    alternativeImage, false
                ));
            }
            List<ProductImageDto> productImageDtos = product.images()
                .stream().map(ProductImage::toDto).toList();

            productDtos.add(product.toDto(optionDtos, productImageDtos));
        }

        return productDtos;
    }
}
