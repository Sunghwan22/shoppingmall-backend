package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.ImageDto;
import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductIdDto;
import kr.megaptera.shoppingMall.dtos.WishDto;
import kr.megaptera.shoppingMall.dtos.WishNumberDto;
import kr.megaptera.shoppingMall.models.Image;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.services.ImageService;
import kr.megaptera.shoppingMall.services.OptionService;
import kr.megaptera.shoppingMall.services.ProductService;
import kr.megaptera.shoppingMall.services.UserService;
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
  private final UserService userService;
  private final WishService wishService;

  public ProductController(ProductService productService,
                           ImageService imageService,
                           OptionService optionService,
                           UserService userService,
                           WishService wishService) {
    this.productService = productService;
    this.imageService = imageService;
    this.optionService = optionService;
    this.userService = userService;
    this.wishService = wishService;
  }

  @GetMapping("/{id}")
  public ProductDto productDetail(
      @PathVariable("id") Long productId) {
    Product product = productService.detail(productId);

    List<ImageDto> imageDtos = imageService.list(productId)
        .stream().map(Image::toDto).toList();

    List<OptionDto> optionDtos = optionService.list(productId)
        .stream().map(Option::toDto).toList();

    List<WishDto> wishDtos = wishService.listByProductId(productId)
        .stream().map(Wish::toDto).toList();

    return product.toDto(imageDtos, optionDtos, wishDtos);
  }

  @PostMapping("/wishes")
  @ResponseStatus(HttpStatus.CREATED)
  public WishNumberDto addWishList(
      @RequestBody ProductIdDto productIdDto,
      @RequestAttribute("userId") Long userId
  ) {

    System.out.println(userId);

    Long productId = productIdDto.getProductId();
    System.out.println(productId);

   int wishNumber = wishService.checkWishList(productId, userId);

    return new WishNumberDto(wishNumber);
  }
}
