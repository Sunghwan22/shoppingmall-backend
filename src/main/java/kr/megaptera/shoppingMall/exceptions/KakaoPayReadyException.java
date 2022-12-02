package kr.megaptera.shoppingMall.exceptions;

import org.springframework.web.client.RestClientException;

public class KakaoPayReadyException extends RuntimeException {
    public KakaoPayReadyException(RestClientException e) {
        super("KakaoPayError " + e);
    }
}
