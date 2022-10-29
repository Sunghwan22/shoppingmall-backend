package kr.megaptera.shoppingMall.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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

    jdbcTemplate.update("" +
        "INSERT INTO product(" +
        "id, category, created_at,  cumulative_sales, description, " +
        "likes, maker, maximum_quantity, price, product_name, " +
        "product_number, stock, updated_at, views, wish)" +
        " VALUES(1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        "전자기기", now, 1200L, "상세설명", 120L, "애플", 5L, 25000L, "아이폰14", 1L,
        5000L, now, 1000L, 250L);

    return "OK";
  }
}
