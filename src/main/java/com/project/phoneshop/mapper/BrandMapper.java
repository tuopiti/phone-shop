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

//public class BrandMapper {
//
// public static Brand toBrand(BrandDTO dto) { 
//	 Brand brand = new Brand();
//     brand.setName(dto.getName()); 
//     return brand; 
// }
//  
// public static BrandDTO toBrandDTO(Brand entity) { 
//	 BrandDTO brandDTO = new
//     BrandDTO(); brandDTO.setName(entity.getName());
//     return brandDTO; 
// }
// 
//}