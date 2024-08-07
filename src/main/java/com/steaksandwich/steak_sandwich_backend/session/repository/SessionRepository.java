package com.steaksandwich.steak_sandwich_backend.session.repository;

import com.steaksandwich.steak_sandwich_backend.session.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findBySessionToken(String sessionToken);
    Optional<Session> findByRefreshToken(String refreshToken);
    void deleteByUserId(Long id);
}
