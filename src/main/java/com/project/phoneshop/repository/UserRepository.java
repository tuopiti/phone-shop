package com.project.phoneshop.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.phoneshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
