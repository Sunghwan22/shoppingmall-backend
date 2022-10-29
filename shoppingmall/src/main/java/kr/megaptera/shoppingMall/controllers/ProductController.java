package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.services.ImageService;
import kr.megaptera.shoppingMall.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;
  private final ImageService imageService;

  public ProductController(ProductService productService, ImageService imageService) {
    this.productService = productService;
    this.imageService = imageService;
  }

  @GetMapping("/{id}")
  public ProductDto productDetail(
      @PathVariable("id") Long productId) {
    Product product = productService.detail(productId);

    List<ProductImageDto> productImageDtos = imageService.list(productId)
        .stream().map(ProductImage::toDto)
        .collect(Collectors.toList());

    return product.toDto(productImageDtos);
  }
}
