package com.blog.repository;

import com.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
//    User findByUsernameAndPassword(String username, String password);
//
//    @Query(value = "SELECT * FROM user where username = ?1 and password = ?2", nativeQuery = true)
//    User login(String username, String password);