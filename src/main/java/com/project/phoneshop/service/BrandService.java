package com.project.phoneshop.service;

import java.util.List;

import com.project.phoneshop.dto.BrandDTO;
import com.project.phoneshop.model.Brand;

//Business Logic Layer;
public interface BrandService {
	Brand save(Brand entity);

	Brand getById(Integer id);

	Brand update(Integer id, BrandDTO dto);

	void delete(Integer id);

	List<Brand> getBrands();
}
