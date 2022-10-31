package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.services.ImageService;
import kr.megaptera.shoppingMall.services.ProductOptionService;
import kr.megaptera.shoppingMall.services.ProductService;
import kr.megaptera.shoppingMall.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
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
  private ProductOptionService productOptionService;

  @Test
  void productDetail() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("products/1"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(
            "애플"
        )));
  }
}
