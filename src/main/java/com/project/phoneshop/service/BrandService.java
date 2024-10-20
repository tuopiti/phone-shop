package com.project.phoneshop.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.project.phoneshop.dto.BrandDTO;
import com.project.phoneshop.model.Brand;

//Business Logic Layer;
public interface BrandService {
	Brand save(Brand entity);

	Brand getById(Long id);

	Brand update(Long id, BrandDTO dto);

	void delete(Long id);

	//List<Brand> getBrands();
	
	//List<Brand> getBrands(Map<String, String> params);
	
	Page<Brand> getBrands(Map<String, String> params);
}
