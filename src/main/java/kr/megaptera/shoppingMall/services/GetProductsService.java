package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.ProductAlternativeImageDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.dtos.ProductListDto;
import kr.megaptera.shoppingMall.dtos.ProductListDtos;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import kr.megaptera.shoppingMall.spectificartions.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    private Specification<Product> specification;

    public GetProductsService(
        ProductRepository productRepository,
        ReviewRepository reviewRepository,
        WishRepository wishRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.wishRepository = wishRepository;
    }

    public ProductListDtos getProducts(Integer page, String keyword) {
        String alternativeImage = "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/NO+IMAGE.gif";
        specification = Specification.where(ProductSpecification.likeProductName(keyword));

        Sort sort = Sort.by("id");
        pageable = PageRequest.of(page - 1, 6, sort);

        List<ProductListDto> productListDtos = new ArrayList<>();
        Page<Product> products = productRepository.findAll(specification, pageable);

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
                    alternativeImage,
                    true
                ));

            ProductListDto productListDto = new ProductListDto(
                product.id(), productImageDto, product.name(), product.price(),
                product.cumulativeSales(), product.createdAt(),
                product.deliveryFee(), reviewNumber, wishNumber);

            productListDtos.add(productListDto);
        }

        return new ProductListDtos(productListDtos, pages());
    }

    public int pages() {
        return productRepository.findAll(specification, pageable).getTotalPages();
    }
}
