package kr.megaptera.shoppingMall.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    public static final String UNREGISTERED = "UNREGISTERED";
    public static final String REGISTERED = "REGISTERED";

    @Id
    @GeneratedValue
    private Long id;

    private String socialLoginId;

    private String identifier;

    private String encodedPassword;

    @Embedded
    private Address address;

    private String name;

    private String recipient;

    private String phoneNumber;

    private String state;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String profileImage;

    private User() {
    }

    public User(
        String socialLoginId,
        String identifier,
        String passwordForSocialLogin,
        String name,
        Address address,
        String phoneNumber,
        String state) {
        this.socialLoginId = socialLoginId;
        this.identifier = identifier;
        this.encodedPassword = passwordForSocialLogin;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.state = state;
    }

    public Long id() {
        return id;
    }

    public String identifier() {
        return identifier;
    }

    public String encodedPassword() {
        return encodedPassword;
    }

    public String name() {
        return name;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public String state() {
        return state;
    }

    public User(
        Long id, String identifier, String encodedPassword,
                String name, Address address, String phoneNumber) {
        this.id = id;
        this.identifier = identifier;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Address address() {
        return address;
    }

    public boolean authenticate(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, this.encodedPassword);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.encodedPassword = passwordEncoder.encode(password);
    }

    public static User fake() {
        return new User(
            1L,
            "tidls45",
            "TJdghks245",
            "조성환",
            new Address(44637L, "울산광역시 정광로 3번길 20", "울산광역시 남구 무거동 1233-12번지", "2층왼쪽"),
            "010-3144-7938"
        );
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String recipient() {
        return recipient;
    }
}
