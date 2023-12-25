package com.project.phoneshop.controller;

import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.phoneshop.dto.BrandDTO;
import com.project.phoneshop.mapper.BrandMapper;
import com.project.phoneshop.model.Brand;
import com.project.phoneshop.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	//@PreAuthorize("hasAuthority('brand:write')")
	//hasAnyAuthority
	@PreAuthorize("hasAnyAuthority('brand:write','brand:read','ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTANCE.toEntity(brandDTO);
		brand = brandService.save(brand);
		return ResponseEntity.ok(brand);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		return ResponseEntity.ok(brandService.getById(id));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody BrandDTO brandDTO){
		return ResponseEntity.ok(brandService.update(id, brandDTO));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
		brandService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('brand:read')")
	@GetMapping
	public ResponseEntity<?> list(){
		
		List<BrandDTO> listBrand = brandService.getBrands().stream()
				.map(b -> BrandMapper.INSTANCE.toDTO(b))
				.toList();
				//.collect(Collectors.toList());
		
		return ResponseEntity.ok(listBrand);
	}
}
