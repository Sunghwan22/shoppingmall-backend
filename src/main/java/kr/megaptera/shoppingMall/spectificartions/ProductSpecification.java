package kr.megaptera.shoppingMall.spectificartions;

import kr.megaptera.shoppingMall.models.Order;
import kr.megaptera.shoppingMall.models.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification {
    public static Specification<Product> likeProductName(String keyword) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("productName"), "%" + keyword + "%");
            }
        };
    }
}
