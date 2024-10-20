package com.project.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.phoneshop.dto.SaleDTO;
import com.project.phoneshop.service.SellService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/sells")
@RequiredArgsConstructor
public class SaleController {
private final SellService sellService;
	
	@PostMapping
	public ResponseEntity<?> sell(@RequestBody SaleDTO dto){
		sellService.sell(dto);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{saleId}/cancel")
	public ResponseEntity<?> cancelSale(@PathVariable Long saleId){
		sellService.cancelSale(saleId);
		return ResponseEntity.noContent().build();
	}
}
