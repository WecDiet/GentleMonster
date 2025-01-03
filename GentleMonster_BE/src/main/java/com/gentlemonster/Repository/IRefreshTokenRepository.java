package com.gentlemonster.Repository;

import com.gentlemonster.Entity.RefreshToken;
import com.gentlemonster.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshtoken(String refreshToken);
    @Modifying
    int deleteByUser(User user);
}
