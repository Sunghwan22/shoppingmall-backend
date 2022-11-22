package kr.megaptera.shoppingMall.services;

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

    public GetProductsService(
        ProductRepository productRepository,
        ReviewRepository reviewRepository,
        WishRepository wishRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.wishRepository = wishRepository;
    }

    public List<ProductListDto> getProducts(Integer page) {
        Sort sort = Sort.by("createdAt");
        Pageable pageable = PageRequest.of(page - 1, 6, sort);

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
                .orElseThrow();

            ProductListDto productListDto = new ProductListDto(
                productImageDto, product.name(), product.price(),
                product.cumulativeSales(), product.createdAt()
                , reviewNumber, wishNumber);

            productListDtos.add(productListDto);
        }

        return productListDtos;
    }
}
