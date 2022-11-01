package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.models.Wish;
import kr.megaptera.shoppingMall.repositoies.WishRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WishService {
  private WishRepository wishRepository;

  public WishService(WishRepository wishRepository) {
    this.wishRepository = wishRepository;
  }

  public List<Wish> list(Long userId) {
   return null;
  }
}
