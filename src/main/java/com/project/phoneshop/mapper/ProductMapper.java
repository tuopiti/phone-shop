package com.project.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.phoneshop.dto.ProductDTO2;
import com.project.phoneshop.dto.ProductDTO;
import com.project.phoneshop.model.Product;
import com.project.phoneshop.service.ColorService;
import com.project.phoneshop.service.ModelService;

@Mapper(componentModel = "spring", uses = {ModelService.class, ColorService.class} )
public interface ProductMapper {
	@Mapping(target = "model", source = "dto.modelId")
	@Mapping(target = "color", source = "dto.colorId")
	Product toProduct(ProductDTO dto);

	@Mapping(target = "modelId", source = "model.id")
	@Mapping(target = "colorId", source = "color.id")
	ProductDTO2 toDTO(Product entity);
}
