package com.gentlemonster.Repository.Specification;

import com.gentlemonster.Entity.Role;
import org.springframework.data.jpa.domain.Specification;

public class RoleSpecification {
    public static Specification<Role> searchRole(String name){
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%")
            );
        };
    }

    public static Specification<Role> fillterRole(String name){
        return Specification.where(searchRole(name));
    }
}
