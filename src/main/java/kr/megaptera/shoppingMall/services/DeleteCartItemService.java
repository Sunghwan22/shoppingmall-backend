package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.exceptions.NoselectCartItemException;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteCartItemService {
    private final CartItemRepository cartItemRepository;

    public DeleteCartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void deleteCartItem(List<Long> cartItemIdList) {
        if(cartItemIdList.size() == 0) {
            throw new NoselectCartItemException();
        }

        for(int i = 0; i< cartItemIdList.size(); i +=1) {
            cartItemRepository.deleteById(
                cartItemIdList.get(i)
            );
        }
    }
}
