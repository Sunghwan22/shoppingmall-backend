package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetWishItemsServiceTest {
    ProductRepository productRepository;
    WishRepository wishRepository;
    GetWishItemsService getWishItemsService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        wishRepository = mock(WishRepository.class);
        getWishItemsService =
            new GetWishItemsService(productRepository, wishRepository);
    }

    @Test
    void getWishProducts() {
        Long userId = 1L;

        Product product = Product.fake(1L);

        given(productRepository.findById(1L))
            .willReturn(Optional.of(product));

        given(productRepository.findById(2L))
            .willReturn(Optional.of(product));

        List<Wish> wishList = List.of(
            new Wish(1L , 1L),
            new Wish(2L , 1L)
        );

        given(wishRepository.findAllByUserId(userId))
            .willReturn(wishList);

        List<ProductDto> productDtos = getWishItemsService.getWishItems(userId);

        assertThat(productDtos).hasSize(2);
    }

}
