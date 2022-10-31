package kr.megaptera.shoppingMall.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "PERSON")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private String identifier;

  private String encodedPassword;

  private String name;

  private String address;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public User() {
  }

  public User(Long id, String identifier, String encodedPassword, String name, String address) {
    this.id = id;
    this.identifier = identifier;
    this.encodedPassword = encodedPassword;
    this.name = name;
    this.address = address;
  }


  public boolean authenticate(PasswordEncoder passwordEncoder, String password) {
    return passwordEncoder.matches(password, this.encodedPassword);
  }

  public void changePassword(String password, PasswordEncoder passwordEncoder) {
    this.encodedPassword = passwordEncoder.encode(password);
  }
}