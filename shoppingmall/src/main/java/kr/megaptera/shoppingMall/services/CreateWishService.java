package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.WishDto;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CreateWishService {
    private WishRepository wishRepository;

    public CreateWishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<WishDto> create(Long productId, Long userId) {
        List<Wish> wishesByProductId = wishRepository.findAllByProductId(productId);

        Wish foundWish = wishesByProductId.stream().filter(
            wish -> wish.userId().equals(userId)).findFirst().orElse(null);

        if (foundWish == null) {
            Wish wish = new Wish(productId, userId);

            wishRepository.save(wish);
        }

        if(foundWish != null) {
            wishRepository.delete(foundWish);
        }

        return wishesByProductId.stream().map(Wish::toDto).toList();
    }
}
