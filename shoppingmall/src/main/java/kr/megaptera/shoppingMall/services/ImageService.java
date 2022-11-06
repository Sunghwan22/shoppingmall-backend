package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.repositoies.ProductImageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ImageService {
  private final ProductImageRepository productImageRepository;

  public ImageService(ProductImageRepository productImageRepository) {
    this.productImageRepository = productImageRepository;
  }

  public List<ProductImage> list(Long productId) {
    return productImageRepository.findAllByProductId(productId);
  }

  public String findThumbNailImage(Long productId) {
    ProductImage thumbNailProductImage =
        productImageRepository.findAllByProductId(productId)
            .stream().filter(image -> image.getThumbnailImage().equals(true))
            .findFirst().orElse(null);

    return thumbNailProductImage != null ? thumbNailProductImage.getUrl() : null;
  }

  public List<ProductImage> findThumbNailImages(List<CartItem> cartItems) {
    List<ProductImage> productImages = new ArrayList<>();

    List<Long> productIdList = cartItems.stream()
        .map(CartItem::getProductId).toList();

    for (Long productId : productIdList) {
      productImages.add(productImageRepository.findByProductId(productId));
    }

    return productImages.stream().filter(
        image -> image.getThumbnailImage().equals(true)).toList();
  }
}
