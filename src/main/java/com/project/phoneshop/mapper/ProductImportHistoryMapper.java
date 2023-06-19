package com.project.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.project.phoneshop.dto.ImportDTO;
import com.project.phoneshop.model.Product;
import com.project.phoneshop.model.ProductImportHistory;

@Mapper
public interface ProductImportHistoryMapper {
	ProductImportHistoryMapper INSTANCE = Mappers.getMapper(ProductImportHistoryMapper.class);
	
	@Mapping(target = "product", source = "product")
	@Mapping(target = "id" , ignore = true)
	ProductImportHistory toEntity(ImportDTO importDTO, Product product);
}
