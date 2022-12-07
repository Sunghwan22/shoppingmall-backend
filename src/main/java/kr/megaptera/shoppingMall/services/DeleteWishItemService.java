package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteWishItemService {
    private final WishRepository wishRepository;

    public DeleteWishItemService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public void deleteWishItem(
        Long productId,
        Long userId) {

        List<Wish> wishList = wishRepository.findAllByUserId(userId);

        for (Wish wish : wishList) {
            if (wish.productId().equals(productId)) {
                wishRepository.deleteByProductId(productId);
            }
        }
    }
}
