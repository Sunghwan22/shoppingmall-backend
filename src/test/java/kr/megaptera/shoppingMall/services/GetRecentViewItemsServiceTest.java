package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.RecentViewItemDtos;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetRecentViewItemsServiceTest {
    GetRecentViewItemsService getRecentViewItemsService;
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        getRecentViewItemsService = new GetRecentViewItemsService(productRepository);
    }

    @Test
    void getRecentViewItems() {
        String productIds = "[1,2]";

        given(productRepository.findById(1L))
            .willReturn(Optional.of(Product.fake(1L)));

        given(productRepository.findById(2L))
            .willReturn(Optional.of(Product.fake(2L)));

        RecentViewItemDtos recentViewItemDtos = getRecentViewItemsService.getRecentViewItems(productIds);

        assertThat(recentViewItemDtos.getRecentViewItems()).hasSize(2);
    }
}