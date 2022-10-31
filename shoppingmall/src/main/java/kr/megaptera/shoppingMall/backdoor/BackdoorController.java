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

    jdbcTemplate.execute("DELETE FROM product");
    jdbcTemplate.execute("DELETE FROM product_image");
    jdbcTemplate.execute("DELETE FROM product_option");

    jdbcTemplate.update("" +
        "INSERT INTO product(" +
        "id, category, created_at,  cumulative_sales, description, " +
        "likes, maker, maximum_quantity, price, product_name, " +
        "product_number, stock, updated_at, views, wish)" +
        " VALUES(1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "전자기기", now, 1200L, "상세설명", 120L, "애플", 5L, 25000L, "아이폰14", 1L,
        5000L, now, 1000L, 250L);

    jdbcTemplate.update("" +
        "INSERT INTO product_image(" +
        "id, is_thumbnail_image, product_Id, url)" +
        "VALUES(1, ?, ?, ?)" ,
        "true", 1L, "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%" +
            "85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1" +
            "%86%BA+2022-10-20+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+10.55.03.png");

    jdbcTemplate.update("" +
            "INSERT INTO product_image(" +
            "id, is_thumbnail_image, product_Id, url)" +
            "VALUES(2, ?, ?, ?)" ,
        "false", 1L, "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%" +
            "85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1" +
            "%86%BA+2022-10-20+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+10.55.03.png");

    jdbcTemplate.update("" +
            "INSERT INTO product_image(" +
            "id, is_thumbnail_image, product_Id, url)" +
            "VALUES(3, ?, ?, ?)" ,
        "false", 1L, "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%" +
            "85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1" +
            "%86%BA+2022-10-20+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+10.55.03.png");

    jdbcTemplate.update("" +
            "INSERT INTO product_option(" +
            "id, add_amount, description, product_id)" +
            "VALUES(1, ?, ?, ?)" ,
            5000L , "블랙", 1L);

    jdbcTemplate.update("" +
            "INSERT INTO product_option(" +
            "id, add_amount, description, product_id)" +
            "VALUES(2, ?, ?, ?)" ,
        10000L , "화이트", 1L);

    jdbcTemplate.update("" +
            "INSERT INTO product_option(" +
            "id, add_amount, description, product_id)" +
            "VALUES(3, ?, ?, ?)" ,
        6000L , "레드", 1L);

    return "OK";
  }

  @GetMapping("setup-user")
  public String setupUser() {
    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM person");

    jdbcTemplate.update("" +
        "INSERT INTO person(" +
        "id, name, identifier, encoded_password, address, " +
        "created_at, updated_at)" +
        " VALUES(1, ?, ?, ?, ?, ?, ?)",
    "코카콜라", "tidls45", passwordEncoder.encode("tjdghks245@"), "스울턱별시 용산구 용산동2가", now, now
    );

    return "OK";
  }
}
