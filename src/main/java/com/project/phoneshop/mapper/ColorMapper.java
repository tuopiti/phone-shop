package com.project.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.project.phoneshop.dto.ColorDTO;
import com.project.phoneshop.model.Color;

@Mapper
public interface ColorMapper {
	ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);
	
	Color toColor(ColorDTO colorDTO);
	
	ColorDTO toColorDTO(Color color);
}
