package com.project.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.project.phoneshop.dto.ModelDTO;
import com.project.phoneshop.model.Model;
import com.project.phoneshop.service.BrandService;

@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelEntityMapper {
	
	ModelEntityMapper INSTANCE = Mappers.getMapper(ModelEntityMapper.class);

	@Mapping(target = "brand", source = "dto.brandId")
	Model toEntity(ModelDTO dto);

	@Mapping(target = "brandId", source = "brand.id")
	ModelDTO toDTO(Model entity);
	
}

/*@Mapper
public interface ModelMapper {
	
	ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

	@Mapping(target = "brand", source = "brandDTO")
	Model toModel(ModelDTO dto);

	@Mapping(target = "brandDTO", source = "brand")
	ModelDTO toDTO(Model entity);
    
}*/
