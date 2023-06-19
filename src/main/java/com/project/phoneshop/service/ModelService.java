package com.project.phoneshop.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.project.phoneshop.model.Model;

public interface ModelService {
	//Model save(ModelDTO dto);
	Model save(Model entity);
	
	Model getById(Long id);
	
	//List<Model> getModels(Map<String, String> params);
	Page<Model> getModels(Map<String, String> params);
}
