package kr.megaptera.shoppingMall.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cart {
  @Id
  @GeneratedValue
  private Long id;

  private Long userId;
    //
  private Long cartItemCount;
  // 장바구니는 회원가입할 떄 만들어 지는 으로 하자.
  // 회원가입할 때 만들어진다. user와 1대1관계
  // 찜한 상품과 최근 본 상품을 가지고 있다.
  // 일괄 구매 선택삭제
  // cart cartItem의 repostiory같은 개념인가? 유저가 cartItem의 id를 가지고 있다.
  // 유저는 장바구니에 넣은 상품이 아니라 장바구니를 들고있고 그 자바구니 안에 아이템이 있는 거니까
  public Cart() {
  }

  public Cart(Long id, Long userId) {
    this.id = id;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }
}
