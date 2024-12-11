package com.gentlemonster.Repository.Specification;

import com.gentlemonster.Entity.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {
    public static Specification<Category> searchCategory(String category, String slug){
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty() || slug == null || slug.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("category")), "%" + category.toLowerCase() + "%" ),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("slug")), "%" + slug.toLowerCase() + "%")
            );

        };
    }

    public static Specification<Category> searchByCategory(String category){
//        List<Predicate> predicates = new ArrayList<>();
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
//            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("slug")), "%" + slug.toLowerCase() + "%"));
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("category")), "%" + category.toLowerCase() + "%");
        };
    }

    public static Specification<Category> searchBySlug(String slug){
        return (root, query, criteriaBuilder) -> {
            if (slug == null || slug.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("slug")), "%" + slug.toLowerCase() + "%");
        };
    }
    public static Specification<Category> fillterCategory(String category, String slug){
        return Specification.where(searchCategory(category, slug))
                .and(searchByCategory(category))
                .and(searchBySlug(slug));
    }
}
