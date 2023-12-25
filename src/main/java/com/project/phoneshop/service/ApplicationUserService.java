package com.project.phoneshop.service;

import java.util.Optional;

import com.project.phoneshop.config.security.AuthUser;

public interface ApplicationUserService {
	Optional<AuthUser> loadUserByUsername(String username);
}
