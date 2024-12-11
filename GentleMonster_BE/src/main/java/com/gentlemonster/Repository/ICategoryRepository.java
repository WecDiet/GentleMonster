package com.gentlemonster.Repository;

import com.gentlemonster.Entity.Category;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface ICategoryRepository extends JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {
    Optional<Category> findByName(String name);
    @NotNull
    Optional<Category> findById(@NotNull UUID categoryId);
}
