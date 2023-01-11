package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.ProductListDto;
import kr.megaptera.shoppingMall.dtos.ProductListDtos;
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
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
        String keyword = "아이폰14";

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

        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(page - 1, 6, sort);

        given(productRepository.findAll(any(Specification.class), eq(pageable)))
            .willReturn(new PageImpl<>(products, pageable, 2));

        given(reviewRepository.findAllByProductId(1L))
            .willReturn(reviews);

        given(wishRepository.findAllByProductId(1L))
            .willReturn(wishes);



        ProductListDtos productListDtos = getProductsService.getProducts(page, keyword);

        assertThat(productListDtos.getProducts().size()).isEqualTo(2);
        assertThat(productListDtos.getProducts().get(0).getWishNumber()).isEqualTo(2);
        assertThat(productListDtos.getProducts().get(0).getPrice()).isEqualTo(1000);
        assertThat(productListDtos.getProducts().get(0).getName()).isEqualTo("아이폰14");

        assertThat(productListDtos.getProducts().get(1).getReviewNumber()).isEqualTo(0);
        assertThat(productListDtos.getProducts().get(1).getWishNumber()).isEqualTo(0);
        assertThat(productListDtos.getProducts().get(1).getName()).isEqualTo("아이폰14");
    }
}
