package kr.megaptera.shoppingMall.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import kr.megaptera.shoppingMall.dtos.ProductOptionDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
  @Id
  @GeneratedValue
  private Long id;
   // 아니 그냥 product가 리스트로 가지고 있으면 되잖아 ? 라는 생각이 도출이
  @GeneratedValue
  private Long productNumber;

  private String productName;

  private String maker;

  private String category;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  private Long views;

  private Long cumulativeSales;

  private Long likes;

  private Long price;

  private Long stock;

  private Long maximumQuantity;

  private String description;

  @JsonManagedReference
  @OneToMany
  @JoinColumn(name = "PRODUCT_ID")
  private List<Image> images;

  @JsonManagedReference
  @OneToMany
  @JoinColumn(name = "PRODUCT_ID")
  private List<Option> options;

  @JsonManagedReference
  @OneToMany
  @JoinColumn(name = "PRODUCT_ID")
  private List<Wish> wishUserList = new ArrayList<>();

  // 상품이 위시리스트를 가지고 있는다? 뭔가 이상함 굳이 갖고 있을 필요가 있나?
  // 해가지고 뭘 할 껀데 뭐 만약에 이 상품에 대해서 뭔가 행사를 화고 유저한테 알려야 한다 라고 하면은 이제

  public Product() {
  }

  public Product(Long id) {
    this.id = id;
  }

  public Product(Long id,
                 Long productNumber,
                 String productName,
                 String maker,
                 String category,
                 Long views,
                 Long cumulativeSales,
                 Long likes,
                 Long price,
                 Long stock,
                 Long maximumQuantity,
                 String description) {

    this.id = id;
    this.productNumber = productNumber;
    this.productName = productName;
    this.maker = maker;
    this.category = category;
    this.views = views;
    this.cumulativeSales = cumulativeSales;
    this.likes = likes;
    this.price = price;
    this.stock = stock;
    this.maximumQuantity = maximumQuantity;
    this.description = description;
  }

  public ProductDto toDto(List<ProductImageDto> productImages, List<ProductOptionDto> productOptions) {
    return new ProductDto(id, productNumber, productName, maker, category,
        createdAt, updatedAt, views,
        cumulativeSales, likes, price,
        stock, maximumQuantity, description, productImages, productOptions);
  }

  public String maker() {
    return maker;
  }

  public int wishes() {
    return wishUserList.size();
  }

  public List<Image> images() {
    return new ArrayList<>(images);
  }

  public List<Option> options() {
    return new ArrayList<>(options);
  }

  public List<Wish> wishUserList() {
    return wishUserList;
  }

  public Long id() {
    return id;
  }
}
