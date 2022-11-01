package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.dtos.ProductOptionDto;
import kr.megaptera.shoppingMall.dtos.WishNumberDto;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.Image;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.services.ImageService;
import kr.megaptera.shoppingMall.services.ProductOptionService;
import kr.megaptera.shoppingMall.services.ProductService;
import kr.megaptera.shoppingMall.services.UserService;
import kr.megaptera.shoppingMall.services.WishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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

    List<ProductImageDto> productImageDtos = imageService.list(productId)
        .stream().map(Image::toDto)
        .collect(Collectors.toList());

    List<ProductOptionDto> productOptionDtos = productOptionService.list(productId)
        .stream().map(Option::toDto)
        .collect(Collectors.toList());

    return product.toDto(productImageDtos, productOptionDtos);
  }

  @GetMapping("wishes/{id}")
  public WishNumberDto addWishList(
      @PathVariable("id") Long productId,
      @RequestAttribute Long userId
  ) {
    int wishNumber = productService.checkWishList(productId, userId);

    return new WishNumberDto(wishNumber);
  }
}
