package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.CartItem;
import kr.megaptera.shoppingMall.models.ProductImage;
import kr.megaptera.shoppingMall.repositoies.ImageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ImageService {
  private final ImageRepository imageRepository;

  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public List<ProductImage> list(Long productId) {
    return imageRepository.findAllByProductId(productId);
  }

  public String findThumbNailImage(Long productId) {
    ProductImage thumbNailProductImage =
        imageRepository.findAllByProductId(productId)
            .stream().filter(image -> image.getThumbnailImage().equals(true))
            .findFirst().orElse(null);

    return thumbNailProductImage.getUrl();
  }

  public List<ProductImage> findThumbNailImages(List<CartItem> cartItems) {
    List<ProductImage> productImages = new ArrayList<>();

    List<Long> productIdList = cartItems.stream()
        .map(CartItem::getProductId).toList();

    for (Long productId : productIdList) {
      productImages.add(imageRepository.findByProductId(productId));
    }

    return productImages.stream().filter(
        image -> image.getThumbnailImage().equals(true)).toList();
  }
}
