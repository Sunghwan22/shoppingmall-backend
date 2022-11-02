package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WishService {
  private final WishRepository wishRepository;

  public WishService(WishRepository wishRepository) {
    this.wishRepository = wishRepository;
  }

  public List<Wish> listByProductId(Long productId) {
    return wishRepository.findAllByProductId(productId);
  }

  public int checkWishList(Long productId, Long userId) {
    List<Wish> userWishList = wishRepository.findAllByUserId(userId);

    Wish foundWish = userWishList.stream()
        .filter(wish -> wish.getProductId().equals(productId))
        .findFirst().orElse(null);

    if(foundWish == null) {
      Wish wish = new Wish(productId, userId);
      wishRepository.save(wish);
    }

    if(foundWish != null) {
      wishRepository.delete(foundWish);
    }

    List<Wish> productWishList = wishRepository.findAllByProductId(productId);

    return productWishList.size();
  }
}
