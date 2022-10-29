package kr.megaptera.shoppingMall.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("backdoor")
public class BackdoorController {
  private final JdbcTemplate jdbcTemplate;

  public BackdoorController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @GetMapping("setup-product")
  public String setupProduct() {
    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM product");
    jdbcTemplate.execute("DELETE FROM product_image");

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

    return "OK";
  }
}
