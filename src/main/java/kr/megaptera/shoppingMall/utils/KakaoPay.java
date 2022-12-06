package kr.megaptera.shoppingMall.utils;


import kr.megaptera.shoppingMall.dtos.OrderProductDto;
import kr.megaptera.shoppingMall.exceptions.KakaoPayReadyException;
import kr.megaptera.shoppingMall.infraStucture.KakaoPayApproval;
import kr.megaptera.shoppingMall.infraStucture.KakaoPayReady;
import kr.megaptera.shoppingMall.repositoies.CartItemRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class KakaoPay {
    private static final String HOST = "https://kapi.kakao.com";
    private final CartItemRepository cartItemRepository;

    private KakaoPayReady kakaoPayReady;

    private KakaoPayApproval kakaoPayApproval;

    private String orderId;

    private Long userId;
    private List<OrderProductDto> orderProducts;

    public KakaoPay(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public String kakaoPayReady(String orderId,
                                Long userId,
                                String productName,
                                Long quantity,
                                Long orderPayment,
                                List<OrderProductDto> orderProducts) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderProducts = orderProducts;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "dd5dd648c5f6938f3b706eb66f3e3040");
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", orderId);
        params.add("partner_user_id", String.valueOf(userId));
        params.add("item_name", productName);
        params.add("quantity", String.valueOf(quantity));
        params.add("total_amount", String.valueOf(orderPayment));
        params.add("tax_free_amount", "10000");
        params.add("approval_url", "http://localhost:8080/order/success");
        params.add("cancel_url", "http://localhost:8080/order/cancel");
        params.add("fail_url", "http://localhost:8080/order/fail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            kakaoPayReady = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReady.class);

            return kakaoPayReady.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            throw new KakaoPayReadyException(e);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public KakaoPayApproval kakaoPayInfo(String pg_token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "dd5dd648c5f6938f3b706eb66f3e3040");
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReady.getTid());
        params.add("partner_order_id", orderId);
        params.add("partner_user_id", String.valueOf(userId));
        params.add("pg_token", pg_token);

        HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<>(params, headers);

        try {
            for (OrderProductDto orderProduct : orderProducts) {
                if (orderProduct.getId() != null) {
                    cartItemRepository.deleteById(orderProduct.getId());
                }
            }

            return restTemplate
                .postForObject(new URI(HOST + "/v1/payment/approve"),
                    requestBody, KakaoPayApproval.class);

        } catch (RestClientException e) {
            throw new KakaoPayReadyException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
