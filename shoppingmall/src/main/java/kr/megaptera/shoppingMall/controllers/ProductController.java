package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.WishNumberDto;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.services.ImageService;
import kr.megaptera.shoppingMall.services.ProductOptionService;
import kr.megaptera.shoppingMall.services.ProductService;
import kr.megaptera.shoppingMall.services.UserService;
import kr.megaptera.shoppingMall.services.WishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;
  private final ImageService imageService;
  private final ProductOptionService productOptionService;
  private final UserService userService;
  private WishService wishService;

  public ProductController(ProductService productService,
                           ImageService imageService,
                           ProductOptionService productOptionService,
                           UserService userService) {
    this.productService = productService;
    this.imageService = imageService;
    this.productOptionService = productOptionService;
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ProductDto productDetail(
      @PathVariable("id") Long productId) {
    Product product = productService.detail(productId);

    return product.toDto();
  }

  @PostMapping("wishes/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public WishNumberDto addWishList(
      @PathVariable("id") Long productId,
      @RequestAttribute("userId") Long userId
  ) {

    int wishNumber = productService.checkWishList(productId, userId);

    return new WishNumberDto(wishNumber);
  }
}
