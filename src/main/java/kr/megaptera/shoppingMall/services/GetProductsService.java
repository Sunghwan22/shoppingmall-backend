package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.ProductAlternativeImageDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.dtos.ProductListDto;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GetProductsService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final WishRepository wishRepository;
    private Pageable pageable;

    public GetProductsService(
        ProductRepository productRepository,
        ReviewRepository reviewRepository,
        WishRepository wishRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.wishRepository = wishRepository;
    }

    public List<ProductListDto> getProducts(Integer page) {
        String alterNativeImage = "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/NO+IMAGE.gif";

        Sort sort = Sort.by("id");
         pageable = PageRequest.of(page - 1, 6, sort);

        List<ProductListDto> productListDtos = new ArrayList<>();

        Page<Product> products = productRepository.findAll(pageable);

        List<Long> productIdList = products.stream().map(
            Product::id).toList();

        for (int i = 0; i < productIdList.size(); i += 1) {
            int reviewNumber =
                reviewRepository.findAllByProductId(productIdList.get(i)).size();

            int wishNumber =
                wishRepository.findAllByProductId(productIdList.get(i)).size();

            Product product = products.getContent().get(i);

            ProductImageDto productImageDto = product.images()
                .stream().filter(ProductImage::getThumbnailImage)
                .findFirst().map(ProductImage::toDto)
                .orElse(new ProductAlternativeImageDto(
                    alterNativeImage,
                    true
                ));
//

            ProductListDto productListDto = new ProductListDto(
                product.id(), productImageDto, product.name(), product.price(),
                product.cumulativeSales(), product.createdAt(),
                product.deliveryFee(), reviewNumber, wishNumber);

            productListDtos.add(productListDto);
        }


        return productListDtos;
    }

    public int pages() {
        return productRepository.findAll(pageable).getTotalPages();
    }
}
