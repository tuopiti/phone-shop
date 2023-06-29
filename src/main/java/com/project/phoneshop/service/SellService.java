package com.project.phoneshop.service;

import com.project.phoneshop.dto.SaleDTO;
import com.project.phoneshop.model.Sale;

public interface SellService {
	void sell(SaleDTO saleDTO);
	void cancelSale(Long saleId);
	Sale getById(Long saleId);
}
