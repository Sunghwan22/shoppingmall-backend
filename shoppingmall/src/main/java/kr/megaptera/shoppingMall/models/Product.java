package kr.megaptera.shoppingMall.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
  @Id
  @GeneratedValue
  @Column(name = "product_id")
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

  private Long price;

  private Long stock;

  private Long maximumQuantity;

  private String description;

  @JsonManagedReference
  @OneToMany(mappedBy = "product")
  private List<Image> images = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "product")
  private final List<Option> options = new ArrayList<>();
  // 네네네 이미지가 통쨰로 들어가면
  @JsonManagedReference
  @OneToMany(mappedBy = "product")
  private final List<Wish> wishUserList = new ArrayList<>();

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
    this.price = price;
    this.stock = stock;
    this.maximumQuantity = maximumQuantity;
    this.description = description;
  }

  public Product(List<Image> images) {
    this.images = images;
  }

  public String maker() {
    return maker;
  }

  public ProductDto toDto() {
    return new ProductDto(id, productNumber,productName,  maker, category
        ,views, cumulativeSales, price, stock, maximumQuantity,description,
        images,options, wishUserList);
  }

  public int wishes() {
    return wishUserList.size();
  }

  public List<Image> images() {
    return images;
  }

  public List<Option> options() {
    return options;
  }

  public List<Wish> wishUserList() {
    return wishUserList;
  }

  public Long id() {
    return id;
  }
}
