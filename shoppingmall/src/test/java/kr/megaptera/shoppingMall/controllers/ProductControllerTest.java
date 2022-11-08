package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.services.ImageService;
import kr.megaptera.shoppingMall.services.OptionService;
import kr.megaptera.shoppingMall.services.ProductService;
import kr.megaptera.shoppingMall.services.UserService;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  @MockBean
  private UserService userService;

  @SpyBean
  private JwtUtil jwtUtil;

  @Test
  void productDetail() throws Exception {
    List<Option> options = List.of(
        new Option(3000L, "블랙")
    );

    List<ProductImage> productImages = List.of(
        new ProductImage("url", true)
    );

    List<Wish> wishes = List.of(
        new Wish(1L)
    );

    Product product = new Product(1L, 1L,productImages, options, wishes, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 5000L, 2L, "상품 설명", 3000L);

    given(productService.detail(1L)).willReturn(product);

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

  @Test
  void wishes() throws Exception {
    String accessToken = jwtUtil.encode(1L);

    List<Option> options = List.of(
        new Option(3000L, "블랙")
    );

    List<ProductImage> productImages = List.of(
        new ProductImage("url", true)
    );

    List<Wish> wishes = new ArrayList<>();

    wishes.add(new Wish(2L));

    Product product = new Product(1L, 1L,productImages, options, wishes, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 5000L, 2L, "상품 설명", 3000L);

    given(productService.detail(1L)).willReturn(product);

    mockMvc.perform(MockMvcRequestBuilders.post("/products/1/wishes")
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    verify(productService).createWish(1L,1L);
  }

  @Test
  void alreadyExistWishes() throws Exception {
    String accessToken = jwtUtil.encode(1L);

    List<Option> options = List.of(
        new Option(3000L, "블랙")
    );

    List<ProductImage> productImages = List.of(
        new ProductImage("url", true)
    );

    List<Wish> wishes = new ArrayList<>();

    wishes.add(new Wish(1L));

    Product product = new Product(1L, 1L,productImages, options, wishes, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 5000L, 2L, "상품 설명", 3000L);

    given(productService.detail(1L)).willReturn(product);

    mockMvc.perform(MockMvcRequestBuilders.post("/products/1/wishes")
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().string(containsString(
            "0"
        )));

    verify(productService).createWish(1L,1L);
  }
}
