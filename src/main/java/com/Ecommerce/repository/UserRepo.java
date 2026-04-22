package com.Ecommerce.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User>  findByEmail(String email);
    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
    
    Optional <User> findById(Long id);
}
