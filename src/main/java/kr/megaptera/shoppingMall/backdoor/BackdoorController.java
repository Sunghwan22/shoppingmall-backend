package kr.megaptera.shoppingMall.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("backdoor")
public class BackdoorController {
  private final JdbcTemplate jdbcTemplate;
  private final PasswordEncoder passwordEncoder;

  public BackdoorController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
    this.jdbcTemplate = jdbcTemplate;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("setup-product")
  public String setupProduct() {
    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM product_options");
    jdbcTemplate.execute("DELETE FROM product_images");
    jdbcTemplate.execute("DELETE FROM product");

    jdbcTemplate.update("" +
            "INSERT INTO product(" +
            "id, category, created_at, cumulative_sales, delivery_fee, description, " +
            "maker, maximum_quantity, price, product_name, " +
            "product_number, stock, updated_at, views)" +
            " VALUES(1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "전자기기", now, 1200L, 3000L, "상세설명", "애플", 5L, 25000L, "아이폰14", 1L,
        5000L, now, 1000L);

    jdbcTemplate.update("" +
            "INSERT INTO product_images(" +
            "product_id, is_thumbnail_image, url)" +
            "VALUES(?, ?, ?)",
        1L, "false", "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/%E1%" +
            "84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3.jpeg");

    jdbcTemplate.update("" +
            "INSERT INTO product_images(" +
            "product_id, is_thumbnail_image, url)" +
            "VALUES(? , ?, ?)",
        1L, "true", "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/%E1%" +
            "84%8B%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%91%E1%85%A9%E1%86%AB14.jpg");

    jdbcTemplate.update("" +
            "INSERT INTO product_images(" +
            "product_id, is_thumbnail_image, url)" +
            "VALUES(?, ?, ?)",
        1L, "false", "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/images.jpeg");


    jdbcTemplate.update("" +
            "INSERT INTO product_images(" +
            "product_id, is_thumbnail_image, url)" +
            "VALUES(?, ?, ?)",
        1L, "false", "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/%E1" +
            "%84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%8" +
            "6%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3+(1).jpeg");

    jdbcTemplate.update("" +
            "INSERT INTO product_options(" +
            "product_id, add_amount, description)" +
            "VALUES(?, ?, ?)",
        1L, 5000L, "화이트");

    jdbcTemplate.update("" +
            "INSERT INTO product_options(" +
            "product_id, add_amount, description)" +
            "VALUES(?, ?, ?)",
        1L, 4000L, "블랙");

    jdbcTemplate.update("" +
            "INSERT INTO product_options(" +
            "product_id, add_amount, description)" +
            "VALUES(?, ?, ?)",
        1L, 3000L, "블루");

    return "OK";
  }

  @GetMapping("setup-review")
  public String setupReviews() {
    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM review_images");
    jdbcTemplate.execute("DELETE FROM review_recommendations");
    jdbcTemplate.execute("DELETE FROM review");

    jdbcTemplate.update("" +
            "INSERT INTO review(" +
            "id, content, created_at, is_best_review, option_name, product_id," +
            "rating, updated_at, user_id, user_nick_name)" +
            "VALUES(1, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "압도적 승리감을 느끼고 싶은가요?", now, "true", "블랙", 1L, 5L, now, 1L, "본인 등장");


    jdbcTemplate.update("" +
            "INSERT INTO review(" +
            "id, content, created_at, is_best_review, option_name, product_id," +
            "rating, updated_at, user_id, user_nick_name)" +
            "VALUES(2, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "압도적 승리감을 느끼고 싶은가요?", now, "true", "블랙", 1L, 5L, now, 1L, "본인 등장");


    jdbcTemplate.update("" +
            "INSERT INTO review(" +
            "id, content, created_at, is_best_review, option_name, product_id," +
            "rating, updated_at, user_id, user_nick_name)" +
            "VALUES(3, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "압도적 승리감을 느끼고 싶은가요?", now, "true", "블랙", 1L, 5L, now, 1L, "본인 등장");

    jdbcTemplate.update("" +
            "INSERT INTO review(" +
            "id, content, created_at, is_best_review, option_name, product_id," +
            "rating, updated_at, user_id, user_nick_name)" +
            "VALUES(4, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "압도적 승리감을 느끼고 싶은가요?", now, "true", "블랙", 1L, 5L, now, 1L, "본인 등장");


    jdbcTemplate.update("" +
            "INSERT INTO review(" +
            "id, content, created_at, is_best_review, option_name, product_id," +
            "rating, updated_at, user_id, user_nick_name)" +
            "VALUES(5, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "2페이지의 베스트 리뷰", now, "true", "블랙", 1L, 5L, now, 1L, "본인 등장");

    jdbcTemplate.update("" +
            "INSERT INTO review(" +
            "id, content, created_at, is_best_review, option_name, product_id," +
            "rating, updated_at, user_id, user_nick_name)" +
            "VALUES(6, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "압도태키 쇼리감?", now, false, "화이트", 1L, 5L, now, 1L, "사이토");

    jdbcTemplate.update("" +
            "INSERT INTO review(" +
            "id, content, created_at, is_best_review, option_name, product_id," +
            "rating, updated_at, user_id, user_nick_name)" +
            "VALUES(7, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "조금의 미소?", now, "false", "블루", 1L, 5L, now, 1L, "본인");

    jdbcTemplate.update("" +
            "INSERT INTO review(" +
            "id, content, created_at, is_best_review, option_name, product_id," +
            "rating, updated_at, user_id, user_nick_name)" +
            "VALUES(8, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "조금의 미소?", now, "false", "블루", 1L, 5L, now, 1L, "본인");

    jdbcTemplate.update("" +
            "INSERT INTO review(" +
            "id, content, created_at, is_best_review, option_name, product_id," +
            "rating, updated_at, user_id, user_nick_name)" +
            "VALUES(9, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "조금의 미소?", now, "false", "블루", 1L, 5L, now, 1L, "본인");

    jdbcTemplate.update("" +
            "INSERT INTO review(" +
            "id, content, created_at, is_best_review, option_name, product_id," +
            "rating, updated_at, user_id, user_nick_name)" +
            "VALUES(10, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "2페이지의 일반 리뷰", "2023-11-11", "false", "블루", 1L, 5L, now, 1L, "본인");

    jdbcTemplate.update("" +
            "INSERT INTO review_images(" +
            "review_id, url)" +
            "VALUES(?, ?)",
        1L, "imageUrl");

    jdbcTemplate.update("" +
            "INSERT INTO review_images(" +
            "review_id, url)" +
            "VALUES(?, ?)",
        2L, "imageUrl");

    jdbcTemplate.update("" +
            "INSERT INTO review_images(" +
            "review_id, url)" +
            "VALUES(? , ?)",
        3L, "imageUrl");

    return "OK";
  }

  @GetMapping("setup-inquiry")
  public String setupInquiry() {
    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM inquiry");

    jdbcTemplate.update("" +
            "INSERT INTO inquiry(" +
            "id, answer_status, content, created_at, is_secret, " +
            "product_id, user_id, user_nick_name)" +
            " VALUES(1, ?, ?, ?, ?, ?, ?, ?)",
        "미답변", "압도적 승리감이 있나요?", now, "true", 1L, 1L, "본인 등장"
    );

    jdbcTemplate.update("" +
            "INSERT INTO inquiry(" +
            "id, answer_status, content, created_at, is_secret, " +
            "product_id, user_id, user_nick_name)" +
            " VALUES(2, ?, ?, ?, ?, ?, ?, ?)",
        "미답변", "조금의 미소만 있으면 된다?", now, "false", 1L, 1L, "본인두두등장"
    );

    jdbcTemplate.update("" +
            "INSERT INTO inquiry(" +
            "id, answer_status, content, created_at, is_secret, " +
            "product_id, user_id, user_nick_name)" +
            " VALUES(3, ?, ?, ?, ?, ?, ?, ?)",
        "답변완료", "정말 재미있는 백도어?", now, "true", 1L, 3L, "본인두두두두두등장"
    );

    jdbcTemplate.update("" +
            "INSERT INTO inquiry(" +
            "id, answer_status, content, created_at, is_secret, " +
            "product_id, user_id, user_nick_name)" +
            " VALUES(4, ?, ?, ?, ?, ?, ?, ?)",
        "미답변", "어떤가요??", now, "true", 1L, 4L, "본인 등장"
    );

    jdbcTemplate.update("" +
            "INSERT INTO inquiry(" +
            "id, answer_status, content, created_at, is_secret, " +
            "product_id, user_id, user_nick_name)" +
            " VALUES(5, ?, ?, ?, ?, ?, ?, ?)",
        "미답변", "아이폰 좋나요?", now, "true", 1L, 5L, "본인 등장"
    );

    jdbcTemplate.update("" +
            "INSERT INTO inquiry(" +
            "id, answer_status, content, created_at, is_secret, " +
            "product_id, user_id, user_nick_name)" +
            " VALUES(6, ?, ?, ?, ?, ?, ?, ?)",
        "미답변", "2페이지의 상품문의입니다", "2023-11-11", "false", 1L, 5L, "본인 등장"
    );

    return "OK";
  }

  @GetMapping("setup-answer")
  public String setupAnswer() {
    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM answer");

    jdbcTemplate.update("" +
            "INSERT INTO answer(" +
            "id, comment, created_at, inquiry_id)" +
            "VALUES(1, ?, ?, ?)",
        "이것은 답변이다", now, 1L);

    jdbcTemplate.update("" +
            "INSERT INTO answer(" +
            "id, comment, created_at, inquiry_id)" +
            "VALUES(2, ?, ?, ?)",
        "이것은 답변이다2", now, 2L);

    jdbcTemplate.update("" +
            "INSERT INTO answer(" +
            "id, comment, created_at, inquiry_id)" +
            "VALUES(3, ?, ?, ?)",
        "이것은 답변이다3", now, 3L);

    jdbcTemplate.update("" +
            "INSERT INTO answer(" +
            "id, comment, created_at, inquiry_id)" +
            "VALUES(4, ?, ?, ?)",
        "이것은 답변이다4", now, 4L);

    jdbcTemplate.update("" +
            "INSERT INTO answer(" +
            "id, comment, created_at, inquiry_id)" +
            "VALUES(5, ?, ?, ?)",
        "이것은 답변이다5", now, 5L);

    return "OK";
  }

  @GetMapping("setup-user")
  public String setupUser() {
    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM users");

    jdbcTemplate.update("" +
            "INSERT INTO users(" +
            "id, address, created_at, encoded_password, identifier, " +
            "name, updated_at)" +
            " VALUES(1, ?, ?, ?, ?, ?, ?)",
        "스을특별시", now, passwordEncoder.encode("tjdghks245@"), "tidls45", "본인 등장", now
    );

    return "OK";
  }

  @GetMapping("setup-wishes")
  public String setupWishes() {
    jdbcTemplate.execute("DELETE FROM wishes");

    jdbcTemplate.update("" +
            "INSERT INTO wishes(" +
            "id, product_id, user_id)" +
            " VALUES(1, ?, ?)",
        1L, 1L
    );

    jdbcTemplate.update("" +
            "INSERT INTO wishes(" +
            "id, product_id, user_id)" +
            " VALUES(2, ?, ?)",
        1L, 2L
    );

    jdbcTemplate.update("" +
            "INSERT INTO wishes(" +
            "id, product_id, user_id)" +
            " VALUES(3, ?, ?)",
        1L, 3L
    );

    return "OK";
  }
}
