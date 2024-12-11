package com.gentlemonster.Repository;

import com.gentlemonster.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface IRoleRepository extends JpaRepository<Role, UUID>, JpaSpecificationExecutor<Role> {
    Optional<Role> findByName(String name);
}
