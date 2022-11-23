package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.ProductImageDto;

import javax.persistence.Embeddable;
import java.util.List;
import java.util.Objects;

@Embeddable
public class ProductImage {
    private String url;

    private Boolean isThumbnailImage;

    public ProductImage() {
    }

    public ProductImage(String url, Boolean isThumbnailImage) {
        this.url = url;
        this.isThumbnailImage = isThumbnailImage;
    }

    public String getUrl() {
        return url;
    }

    public Boolean getThumbnailImage() {
        return isThumbnailImage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, isThumbnailImage);
    }

    @Override
    public boolean equals(Object other) {
        ProductImage otherProductImage = (ProductImage) other;

        return this.url.equals(otherProductImage.url) &&
            this.isThumbnailImage.equals(otherProductImage.isThumbnailImage);
    }

    @Override
    public String toString() {
        return "ProductImage{" +
            "url='" + url + '\'' +
            ", isThumbnailImage=" + isThumbnailImage +
            '}';
    }

    public static List<ProductImage> fake() {
        return List.of(
            new ProductImage("url", true),
            new ProductImage("url", false)
        );
    }

    public ProductImageDto toDto() {
        return new ProductImageDto(url, isThumbnailImage);
    }

    public static ProductImage alternativeImage() {
        return new ProductImage(
            "https://test-s3-image.s3.ap-northeast-2.amazonaws.com/NO+IMAGE.gif"
            , true);
    }
}
