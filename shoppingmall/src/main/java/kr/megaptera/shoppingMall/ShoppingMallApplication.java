package kr.megaptera.shoppingMall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@SpringBootApplication
public class ShoppingMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingMallApplication.class, args);
	}

	@Bean
	public WebSecurityCustomizer ignoringCustomizer() {
			return (web -> web.ignoring().antMatchers("/**"));
	}
}
