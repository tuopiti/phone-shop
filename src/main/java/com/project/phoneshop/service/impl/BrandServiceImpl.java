package com.project.phoneshop.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.phoneshop.dto.BrandDTO;
import com.project.phoneshop.exception.ApiException;
import com.project.phoneshop.model.Brand;
import com.project.phoneshop.repository.BrandRepository;
import com.project.phoneshop.service.BrandService;
import com.project.phoneshop.spec.BrandFilter;
import com.project.phoneshop.spec.BrandSpec;
import com.project.phoneshop.utils.PageUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Business Layer
@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{

	@Autowired
	private final BrandRepository brandRepository;
	
	@Override
	public Brand save(Brand entity) {
		return brandRepository.save(entity);	
	}

	@Override
	public Brand getById(Long id){
		  return brandRepository.findById(id)
				 .orElseThrow(()-> new ApiException(HttpStatus.NOT_FOUND,String.format("brand not found for id=%d", id)));
				      //.orElseThrow(() -> new ResourceNotFoundException("Brand", id));
					
		
		//		Optional<Brand> brandOptional = brandRepository.findById(id);
		//
		//		if (brandOptional.isPresent()) {
		//			return brandOptional.get();
		//		} else {
		//			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("brand not found for id=%d", id));
		//		}
	}

	@Override
	public Brand update(Long id, BrandDTO dto) {
		Brand brand = getById(id);
		brand.setName(dto.getName());
		return brandRepository.save(brand);
	}

	@Override
	public void delete(Long id){
		Brand brand = getById(id);
		//brandRepository.delete(brand);
		
		brand.setActive(false);
		brandRepository.save(brand);
		log.info("brand with id = %d id is delete".formatted(id));
		// log.info(String.format("brand with id = %d id is delete", id));

	}

	/*
	@Override
	public List<Brand> getBrands() {
		//System.out.println("============");
		//boolean existsByName = brandRepository.existsByName("Nokia");
		//System.out.println(existsByName);
		//System.out.println("============");
		
		//return brandRepository.findAll();
		
		return brandRepository.findByActiveTrue();
	}

    */
	
	/*
	@Override
	public List<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();
		
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);
		}
		
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setId(Integer.parseInt(id));
		}
				
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		
		return brandRepository.findAll(brandSpec);
		
	}
	
	*/
	
	@Override
	public Page<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();
		
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);
		}
		
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setId(Integer.parseInt(id));
		}
		// @TODO add to a function for pageable
		int pageLimit = PageUtils.PAGE_SIZE_DEAFAULT;
		if(params.containsKey(PageUtils.PAGE_SIZE)) {
			pageLimit = Integer.parseInt(params.get(PageUtils.PAGE_SIZE));
		}
		
		int pageNumber = PageUtils.PAGE_NUMBER_DEFAULT;
		if(params.containsKey(PageUtils.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtils.PAGE_NUMBER));
		}
		
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		
		Pageable pageable = PageUtils.getPageable(pageNumber, pageLimit);
		
		//Pageable
		//Page<Brand> findAll = brandRepository.findAll(brandSpec, org.springframework.data.domain.Pageable.ofSize(0));
		
		 Page<Brand> page = brandRepository.findAll(brandSpec, pageable);
		return page;
	}
	
}
