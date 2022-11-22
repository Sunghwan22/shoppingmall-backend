package kr.megaptera.shoppingMall.models;

import kr.megaptera.shoppingMall.dtos.OptionDto;

import javax.persistence.Embeddable;
import java.util.List;
import java.util.Objects;

@Embeddable
public class Option {
    private Long addAmount;

    private String description;

    public Option() {
    }

    public Option(Long addAmount, String description) {
        this.addAmount = addAmount;
        this.description = description;
    }

    public Long getAddAmount() {
        return addAmount;
    }

    public String getDescription() {
        return description;
    }

    public static List<Option> fake() {
        return List.of(
            new Option(3000L, "상품 설명")
        );
    }

    @Override
    public boolean equals(Object other) {
        Option otherOption = (Option) other;

        return this.addAmount.equals(otherOption.addAmount) &&
            this.description.equals(((Option) other).description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addAmount, description);
    }

    public OptionDto toDto() {
        return new OptionDto(addAmount, description);
    }
}
