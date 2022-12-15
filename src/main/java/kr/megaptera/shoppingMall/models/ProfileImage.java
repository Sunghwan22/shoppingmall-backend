package kr.megaptera.shoppingMall.models;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ProfileImage {
    private String url;

    public ProfileImage(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileImage that = (ProfileImage) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "ProfileImage{" +
            "url='" + url + '\'' +
            '}';
    }
}
