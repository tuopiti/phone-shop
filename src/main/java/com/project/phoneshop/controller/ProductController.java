package com.project.phoneshop.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.phoneshop.dto.PageDTO;
import com.project.phoneshop.dto.PriceDTO;
import com.project.phoneshop.dto.ProductImportDTO;
import com.project.phoneshop.mapper.PageMapper;
import com.project.phoneshop.mapper.ProductMapper;
import com.project.phoneshop.model.Product;
import com.project.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
public class ProductController {
	private final ProductMapper productMapper;
	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody ProductImportDTO dto){
		return ResponseEntity.ok(productMapper.toDTO(productService.save(dto)));
	}
	
	
	@PutMapping("/setPrice/{productId}")
	public ResponseEntity<?> setPrice(@PathVariable("productId") Long productId,
			@RequestBody PriceDTO priceDTO){
		Product product = productService.setPrice(productId, priceDTO.getSalePrice());
		return ResponseEntity.ok(productMapper.toDTO(product));
	}
	
	
	@GetMapping
	public ResponseEntity<?> listProducts(@RequestParam Map<String, String> params){
		Page<Product> productPage = productService.getProducts(params);
		
		PageDTO pageDTO = PageMapper.INSTANCE.toDTO(productPage);
		pageDTO.setList(productService.toProductDisplayDTOs(productPage.getContent()));
		
		return ResponseEntity.ok(pageDTO);
	}
	
	@PostMapping("uploadProducts")
	public ResponseEntity<?> uploadProoducts(@RequestParam("file") MultipartFile file){
		Map<Long, String> errorMap = productService.uploadProducts(file);
		return ResponseEntity.ok(errorMap);
	}
}
