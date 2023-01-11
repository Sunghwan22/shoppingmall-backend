package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.OrderDto;
import kr.megaptera.shoppingMall.dtos.WriteableReviewProductsDto;
import kr.megaptera.shoppingMall.services.GetWriteableReviewOrderService;
import kr.megaptera.shoppingMall.services.GetWriteableReviewOrdersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WriteableReviewOrderController {
    private final GetWriteableReviewOrdersService getWriteableReviewOrdersService;
    private final GetWriteableReviewOrderService getWriteableReviewOrderService;

    public WriteableReviewOrderController(
        GetWriteableReviewOrdersService getWriteableReviewOrdersService,
        GetWriteableReviewOrderService getWriteableReviewOrderService) {
        this.getWriteableReviewOrdersService = getWriteableReviewOrdersService;
        this.getWriteableReviewOrderService = getWriteableReviewOrderService;
    }

    @GetMapping("/user/me/writeableReviewOrders")
    public WriteableReviewProductsDto productList(
        @RequestAttribute("userId") Long userId,
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {

        return getWriteableReviewOrdersService.getOrders(userId, page);
    }

    @GetMapping("/user/me/writeableReviewOrders/{id}")
    public OrderDto detail(
        @PathVariable("id") Long id
    ) {
       return getWriteableReviewOrderService.getOrder(id);
    }
}
