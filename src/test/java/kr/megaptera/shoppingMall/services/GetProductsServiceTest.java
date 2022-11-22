package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.ProductListDto;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductsServiceTest {
    GetProductsService getProductsService;
    ProductRepository productRepository;
    WishRepository wishRepository;
    ReviewRepository reviewRepository;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        wishRepository = mock(WishRepository.class);
        reviewRepository = mock(ReviewRepository.class);
        getProductsService = new GetProductsService(
            productRepository,
            reviewRepository,
            wishRepository);
    }

    @Test
    void getProducts() {
        List<Product> products = List.of(
            Product.fake(1L),
            Product.fake(2L)
        );

        List<Review> reviews = List.of(
            Review.fake(1L),
            Review.fake(1L)
        );

        List<Wish> wishes = List.of(
            new Wish(1L, 1L),
            new Wish(1L, 2L)
        );

        int page = 1;

        Sort sort = Sort.by("createdAt");
        Pageable pageable = PageRequest.of(page - 1, 6, sort);

        given(productRepository.findAll(pageable))
            .willReturn(new PageImpl<>(products, pageable, 2));

        given(reviewRepository.findAllByProductId(1L))
            .willReturn(reviews);

        given(wishRepository.findAllByProductId(1L))
            .willReturn(wishes);

        List<ProductListDto> productListDtos = getProductsService.getProducts(page);

        assertThat(productListDtos.get(0).getReviewNumber()).isEqualTo(2);
        assertThat(productListDtos.get(0).getWishNumber()).isEqualTo(2);
        assertThat(productListDtos.get(0).getPrice()).isEqualTo(1000);
        assertThat(productListDtos.get(0).getName()).isEqualTo("아이폰14");

        assertThat(productListDtos.get(1).getReviewNumber()).isEqualTo(0);
        assertThat(productListDtos.get(1).getWishNumber()).isEqualTo(0);
        assertThat(productListDtos.get(1).getName()).isEqualTo("아이폰14");
    }
}
