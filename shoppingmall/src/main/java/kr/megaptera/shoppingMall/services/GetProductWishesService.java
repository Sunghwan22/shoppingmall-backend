package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.WishDto;
import kr.megaptera.shoppingMall.dtos.WishDtos;
import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetProductWishesService {
    private WishRepository wishRepository;

    public GetProductWishesService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public WishDtos getProductWishes(Long productId) {
        List<Wish> wishes = wishRepository.findAllByProductId(productId);

        List<WishDto> wishDtos = wishes.stream().map(Wish::toDto).toList();

        return new WishDtos(wishDtos);
    }
}
