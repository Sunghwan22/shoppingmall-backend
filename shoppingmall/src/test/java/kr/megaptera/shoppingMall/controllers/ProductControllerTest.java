package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.repositoies.ProductRepository;
import kr.megaptera.shoppingMall.services.ProductService;
import kr.megaptera.shoppingMall.services.UserService;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private UserService userService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void productDetail() throws Exception {
        Product product = Product.fake(1L);

        List<ProductImageDto> productImageDtos =
            ProductImage.fake().stream().map(
                ProductImage::toDto).toList();

        List<OptionDto> optionDtos  = Option.fake().stream().map(
            Option::toDto).toList();

        given(productRepository.findById(1L))
            .willReturn(Optional.of(product));

        given(productService.detail(1L))
            .willReturn(product.toDto(optionDtos, productImageDtos));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(
                "5000"
            )))
            .andExpect(content().string(containsString(
                "1000"
            )))
            .andExpect(content().string(containsString(
                "3000"
            )));
    }
}
