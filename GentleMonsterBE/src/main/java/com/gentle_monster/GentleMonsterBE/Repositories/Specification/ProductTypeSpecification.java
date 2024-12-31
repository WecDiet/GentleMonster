package com.gentlemonster.Repository.Specification;

import com.gentlemonster.Entity.ProductType;
import org.springframework.data.jpa.domain.Specification;

public class ProductTypeSpecification {
    public static Specification<ProductType> searchProductType(String name){
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%")
            );
        };
    }

    public static Specification<ProductType> fillterProductType(String name){
        return Specification.where(searchProductType(name));
    }
}
