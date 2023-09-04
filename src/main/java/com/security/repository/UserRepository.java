package com.security.repository;

import com.security.entities.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
@EnableAutoConfiguration
public interface UserRepository extends JpaRepository<User,Long> {
    // Since email is unique, we'll find users by email
    Optional<User> findByEmail(String email);
}
