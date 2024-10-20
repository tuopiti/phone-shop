package com.project.phoneshop.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.phoneshop.dto.ModelDTO;
import com.project.phoneshop.dto.PageDTO;
import com.project.phoneshop.mapper.ModelEntityMapper;
import com.project.phoneshop.mapper.PageMapper;
import com.project.phoneshop.model.Model;
import com.project.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/models")
public class ModelController {
	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;
	/*
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO dto){
		Model model = modelService.save(dto);
		ModelDTO modelDTO = ModelMapper.INSTANCE.toDTO(model);
		return ResponseEntity.ok(modelDTO);
	}
	*/
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO dto){
		
		Model model = modelMapper.toEntity(dto);
		model = modelService.save(model);
		ModelDTO modelDTO = ModelEntityMapper.INSTANCE.toDTO(model);
		return ResponseEntity.ok(modelDTO);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		Model model = modelService.getById(id);
		return ResponseEntity.ok(ModelEntityMapper.INSTANCE.toDTO(model));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_SALE','ROLE_ADMIN')")
	//@RolesAllowed("ROLE_SALE")
	@GetMapping
	public ResponseEntity<?> getModelList(@RequestParam Map<String, String> params){
		Page<Model> page = modelService.getModels(params);
		
		PageDTO dto = PageMapper.INSTANCE.toDTO(page);
		dto.setList(page.get().map(ModelEntityMapper.INSTANCE::toDTO).toList());
		return ResponseEntity.ok(dto);
	}
}
