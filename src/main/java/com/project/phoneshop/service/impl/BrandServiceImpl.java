package com.project.phoneshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.phoneshop.dto.BrandDTO;
import com.project.phoneshop.exception.ApiException;
import com.project.phoneshop.model.Brand;
import com.project.phoneshop.repository.BrandRepository;
import com.project.phoneshop.service.BrandService;

import lombok.extern.slf4j.Slf4j;

//Business Layer
@Slf4j
@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private BrandRepository brandRepository;
	
	@Override
	public Brand save(Brand entity) {
		return brandRepository.save(entity);	
	}

	@Override
	public Brand getById(Integer id) {
		    return brandRepository.findById(id)
				 .orElseThrow(()-> new ApiException(HttpStatus.NOT_FOUND,String.format("brand not found for id=%d", id)));
			/*
			 * if(brandOptional.isPresent()) { return brandOptional.get(); }else { throw new
			 * HttpClientErrorException(HttpStatus.NOT_FOUND,
			 * String.format("brand not found for id=%d", id)); }
			 */
	}

	@Override
	public Brand update(Integer id, BrandDTO dto) {
		Brand brand = getById(id);
		brand.setName(dto.getName());
		return brandRepository.save(brand);
	}

	@Override
	public void delete(Integer id) {
		Brand brand = getById(id);
		brandRepository.delete(brand);
		log.info("brand with id = %d id is delete".formatted(id));
		//log.info(String.format("brand with id = %d id is delete", id));
		
	}

	@Override
	public List<Brand> getBrands() {
		return brandRepository.findAll();
	}
	
}
