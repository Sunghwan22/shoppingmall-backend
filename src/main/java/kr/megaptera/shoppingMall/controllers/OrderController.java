package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.KakaoPayApprovalDto;
import kr.megaptera.shoppingMall.dtos.OrderDto;
import kr.megaptera.shoppingMall.dtos.OrderRequestDto;
import kr.megaptera.shoppingMall.services.CreateOrderService;
import kr.megaptera.shoppingMall.utils.KakaoPay;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final CreateOrderService createOrderService;
    private final KakaoPay kakaoPay;

    public OrderController(
        CreateOrderService createOrderService,
        KakaoPay kakaoPay) {
        this.createOrderService = createOrderService;
        this.kakaoPay = kakaoPay;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto order(
        @RequestAttribute("userId") Long userId,
        @Validated @RequestBody OrderRequestDto orderRequestDto
    ) {
        return createOrderService.createOrder(userId, orderRequestDto);
    }

    @GetMapping("/kakaoPaySuccess")
    public KakaoPayApprovalDto orderResult(
        @RequestParam("pg_token") String pgToken
    ) {

        return kakaoPay.kakaoPayInfo(pgToken).toDto();
    }
}
