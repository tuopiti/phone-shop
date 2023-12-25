package com.project.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.project.phoneshop.config.security.AuthUser;
import com.project.phoneshop.model.User;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	AuthUser toAuthUser(User user);
}
