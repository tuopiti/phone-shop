package com.project.phoneshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.phoneshop.exception.ResourceNotFoundException;
import com.project.phoneshop.model.Color;
import com.project.phoneshop.repository.ColorRepository;
import com.project.phoneshop.service.ColorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService{
	@Autowired
	private final ColorRepository colorRepository;

	@Override
	public Color save(Color entity) {
		return colorRepository.save(entity);
	}

	@Override
	public Color getById(Long id) {
		  return colorRepository.findById(id)
				 .orElseThrow(() -> new ResourceNotFoundException("Color", id));
	}
}
