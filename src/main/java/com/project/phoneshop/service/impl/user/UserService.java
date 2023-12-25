package com.project.phoneshop.service.impl.user;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.project.phoneshop.config.security.AuthUser;
import com.project.phoneshop.exception.ApiException;
import com.project.phoneshop.mapper.UserMapper;
import com.project.phoneshop.model.Role;
import com.project.phoneshop.model.User;
import com.project.phoneshop.repository.UserRepository;
import com.project.phoneshop.service.ApplicationUserService;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class UserService implements ApplicationUserService{
	private final UserRepository userRepository;

	@Override
	public Optional<AuthUser> loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User not found %s".formatted(username)));
		AuthUser authUser = UserMapper.INSTANCE.toAuthUser(user);
		
		Set<SimpleGrantedAuthority> authorities = user.getRoles()
			.stream()
			.flatMap(role -> toStreamOfSimpleGrantedAuthority(role))
			.collect(Collectors.toSet());
		authUser.setGrantedAuthorities(authorities);
		return Optional.ofNullable(authUser);
	}
	
	private Stream<SimpleGrantedAuthority> toStreamOfSimpleGrantedAuthority(Role role){
		Set<SimpleGrantedAuthority> permissions = role.getPermissions().stream()
				.map(p -> new SimpleGrantedAuthority(p.getName()))
				.collect(Collectors.toSet());
			permissions.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
			return permissions.stream();
	}
}
