package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.WishNumberDto;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{id}")
  public ProductDto productDetail(
      @PathVariable("id") Long productId) {
    ProductDto productDto = productService.detail(productId);

    return productDto;
  }

  @PostMapping("/{id}/wishes")
  @ResponseStatus(HttpStatus.CREATED)
  public WishNumberDto addWishList(
      @PathVariable("id") Long productId,
      @RequestAttribute("userId") Long userId
  ) {
    int wishNumber = productService.createWish(productId, userId);

    return new WishNumberDto(wishNumber);
  }
}
