package com.gentlemonster.Repository;

import com.gentlemonster.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface IProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByIsActive(boolean isActive);
    Long countAllByIsActive(boolean isActive);
}