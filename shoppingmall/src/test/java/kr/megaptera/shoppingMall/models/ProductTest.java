package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.OptionDto;
import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductImageDto;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Port;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    @Test
    void toDto() {
        List<Option> options = List.of(
            new Option(3000L, "블랙")
        );

        List<ProductImage> productImages = List.of(
            new ProductImage("url", true)
        );

        Product product = new Product(
            1L,
            1L,
            productImages,
            options,
            "아이폰14",
            "애플",
            "전자기기",
            1000L,
            120L,
            1000L,
            5000L,
            2L,
            "상품 설명",
            3000L);

        List<OptionDto> optionDtos = options.stream().map(
            Option::toDto).toList();

        List<ProductImageDto> productImageDtos = productImages.stream().map(
            ProductImage::toDto).toList();

        ProductDto productDto = product.toDto(optionDtos, productImageDtos);

        assertThat(productDto.getMaker()).isEqualTo("애플");
        assertThat(productDto.getId()).isEqualTo(1L);
    }

    @Test
    void fake() {
        Product product= Product.fake(1L);

        assertThat(product).isNotNull();
        assertThat(product.maker()).isEqualTo("애플");
    }
}