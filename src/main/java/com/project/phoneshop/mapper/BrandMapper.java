package com.project.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.project.phoneshop.dto.BrandDTO;
import com.project.phoneshop.model.Brand;

@Mapper
public interface BrandMapper {
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	
	Brand toEntity(BrandDTO dto);
	
	BrandDTO toDTO(Brand entity);
}
