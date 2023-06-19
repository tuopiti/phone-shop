package com.project.phoneshop.service;

import java.util.List;

import com.project.phoneshop.dto.BrandDTO;
import com.project.phoneshop.model.Brand;

//Business Logic Layer;
public interface BrandService {
	Brand save(Brand entity);

	Brand getById(Long id);

	Brand update(Long id, BrandDTO dto);

	void delete(Long id);

	List<Brand> getBrands();
}
