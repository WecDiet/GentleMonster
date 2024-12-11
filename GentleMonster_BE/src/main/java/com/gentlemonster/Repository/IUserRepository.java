package com.gentlemonster.Repository;

import com.gentlemonster.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findByEmail(String email);
}
