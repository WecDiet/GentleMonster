package com.gentlemonster.Repository;


import com.gentlemonster.Entity.ProductType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface IProductTypeRepository extends JpaRepository<ProductType, UUID>, JpaSpecificationExecutor<ProductType> {
    Optional<ProductType> findByName(String name);
    @NotNull Optional<ProductType> findById(@NotNull UUID productTypeId);
}
