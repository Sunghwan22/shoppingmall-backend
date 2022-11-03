package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.services.ImageService;
import kr.megaptera.shoppingMall.services.OptionService;
import kr.megaptera.shoppingMall.services.ProductService;
import kr.megaptera.shoppingMall.services.UserService;
import kr.megaptera.shoppingMall.services.WishService;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

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
  private ImageService imageService;

  @MockBean
  private UserService userService;

  @MockBean
  private OptionService optionService;

  @MockBean
  private WishService wishService;

  @SpyBean
  private JwtUtil jwtUtil;

  @Test
  void productDetail() throws Exception {
    Product product = new Product(1L, 1L, "아이폰14", "애플", "전자기기", 1000L, 120L,
        1000L, 5000L, 2L, "상품 설명", 3000L);

    List<ProductImage> productImages = List.of(
        new ProductImage(1L, 1L, "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2022-10-20+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+10.55.03.png", true)
    );

    given(productService.detail(1L)).willReturn(product);

    mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(
            "5000"
        )));

  }

  @Test
  void wishes() throws Exception {
    String accessToken = jwtUtil.encode(1L);

    given(wishService.checkWishList(1L, 1L)).willReturn(2);

    mockMvc.perform(MockMvcRequestBuilders.post("/products/wishes")
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"productId\" : \"1\"}"))
        .andExpect(status().isCreated());
  }
}
