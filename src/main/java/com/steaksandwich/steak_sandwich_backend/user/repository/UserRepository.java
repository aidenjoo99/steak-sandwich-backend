package com.steaksandwich.steak_sandwich_backend.user.repository;

import com.steaksandwich.steak_sandwich_backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}