package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductIdDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.dtos.WishDto;
import kr.megaptera.shoppingMall.dtos.WishNumberDto;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.services.ImageService;
import kr.megaptera.shoppingMall.services.OptionService;
import kr.megaptera.shoppingMall.services.ProductService;
import kr.megaptera.shoppingMall.services.WishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;
  private final ImageService imageService;
  private final OptionService optionService;
  private final WishService wishService;

  public ProductController(ProductService productService,
                           ImageService imageService,
                           OptionService optionService,
                           WishService wishService)
  {
    this.productService = productService;
    this.imageService = imageService;
    this.optionService = optionService;
    this.wishService = wishService;
  }

  @GetMapping("/{id}")
  public ProductDto productDetail(
      @PathVariable("id") Long productId) {
    Product product = productService.detail(productId);

    List<ProductImageDto> productImageDtos = imageService.list(productId)
        .stream().map(ProductImage::toDto).toList();

    List<OptionDto> optionDtos = optionService.list(productId)
        .stream().map(Option::toDto).toList();

    List<WishDto> wishDtos = wishService.listByProductId(productId)
        .stream().map(Wish::toDto).toList();

    return product.toDto(
        productImageDtos,
        optionDtos,
        wishDtos);
  }

  @PostMapping("/wishes")
  @ResponseStatus(HttpStatus.CREATED)
  public WishNumberDto addWishList(
      @RequestBody ProductIdDto productIdDto,
      @RequestAttribute("userId") Long userId
  ) {

    Long productId = productIdDto.getProductId();

   int wishNumber = wishService.checkWishList(productId, userId);

    return new WishNumberDto(wishNumber);
  }
}
