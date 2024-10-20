package com.project.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.phoneshop.dto.ColorDTO;
import com.project.phoneshop.mapper.ColorMapper;
import com.project.phoneshop.model.Color;
import com.project.phoneshop.service.ColorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/color")
@AllArgsConstructor
public class ColorController {

	private ColorService colorService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ColorDTO colorDTO){
		Color color = ColorMapper.INSTANCE.toColor(colorDTO);
		return ResponseEntity.ok(colorService.save(color));
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		return ResponseEntity.ok(colorService.getById(id));
	}
}
