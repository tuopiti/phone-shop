package com.project.phoneshop.service;

import com.project.phoneshop.model.Color;

public interface ColorService {
	Color save(Color entity);

	Color getById(Long id);
}
