package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.ImageDto;
import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductIdDto;
import kr.megaptera.shoppingMall.dtos.ReviewDto;
import kr.megaptera.shoppingMall.dtos.ReviewImageDto;
import kr.megaptera.shoppingMall.dtos.WishDto;
import kr.megaptera.shoppingMall.dtos.WishNumberDto;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.models.Option;
import kr.megaptera.shoppingMall.models.Product;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.models.ReviewImage;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.services.ImageService;
import kr.megaptera.shoppingMall.services.OptionService;
import kr.megaptera.shoppingMall.services.ProductService;
import kr.megaptera.shoppingMall.services.ReviewImageService;
import kr.megaptera.shoppingMall.services.ReviewService;
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
  private final ReviewService reviewService;
  private final ReviewImageService reviewImageService;

  public ProductController(ProductService productService,
                           ImageService imageService,
                           OptionService optionService,
                           WishService wishService,
                           ReviewService reviewService,
                           ReviewImageService reviewImageService) {
    this.productService = productService;
    this.imageService = imageService;
    this.optionService = optionService;
    this.wishService = wishService;
    this.reviewService = reviewService;
    this.reviewImageService = reviewImageService;
  }

  @GetMapping("/{id}")
  public ProductDto productDetail(
      @PathVariable("id") Long productId) {
    Product product = productService.detail(productId);

    List<ImageDto> imageDtos = imageService.list(productId)
        .stream().map(ProductImage::toDto).toList();

    List<OptionDto> optionDtos = optionService.list(productId)
        .stream().map(Option::toDto).toList();

    List<WishDto> wishDtos = wishService.listByProductId(productId)
        .stream().map(Wish::toDto).toList();

    List<ReviewDto> reviewDtos = reviewService.listByProductId(productId)
        .stream().map(Review::toDto).toList();

    List<ReviewImageDto> reviewImageDtos = reviewImageService.listByProductId(productId)
        .stream().map(ReviewImage::toDto).toList();

    // 리뷰를 추려주는 건 따로 해야 겠다. 프론트엔드에서 찾아서 해야함
    return product.toDto(imageDtos, optionDtos, wishDtos, reviewDtos, reviewImageDtos);
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
