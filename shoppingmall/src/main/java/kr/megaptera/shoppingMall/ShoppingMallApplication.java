package kr.megaptera.shoppingMall;

import kr.megaptera.shoppingMall.interceptors.AuthenticationInterceptor;
import kr.megaptera.shoppingMall.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ShoppingMallApplication {
  @Value("${jwt.secret}")
  private String jwtSecret;

  public static void main(String[] args) {
    SpringApplication.run(ShoppingMallApplication.class, args);
  }

  @Bean
  public WebSecurityCustomizer ignoringCustomizer() {
    return (web -> web.ignoring().antMatchers("/**"));
  }

  @Bean
  public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
      }
    };
  }

  @Bean
  public AuthenticationInterceptor authenticationInterceptor() {
    return new AuthenticationInterceptor(jwtUtil());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new Argon2PasswordEncoder();
  }


  @Bean
  public JwtUtil jwtUtil() {
    return new JwtUtil(jwtSecret);
  }
}
