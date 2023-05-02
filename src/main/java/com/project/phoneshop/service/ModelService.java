package com.project.phoneshop.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.project.phoneshop.dto.ModelDTO;
import com.project.phoneshop.model.Model;

public interface ModelService {
	Model save(ModelDTO dto);
	
	Model getById(Integer id);
	
	Page<Model> getModels(Map<String, String> params);
}
