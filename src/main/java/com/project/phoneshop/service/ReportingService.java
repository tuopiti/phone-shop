package com.project.phoneshop.service;

import java.time.LocalDate;
import java.util.List;

import com.project.phoneshop.dto.ExpenseDTO;
import com.project.phoneshop.dto.ProductSoldDTO;
import com.project.phoneshop.dto.SaleByDateDTO;
import com.project.phoneshop.projections.SaleByDate;

public interface ReportingService {
	// daily sale by product
	List<SaleByDate> getProductSoldByDate(LocalDate soldDate);
		
	List<SaleByDateDTO> getProductSoldByDateV2(LocalDate soldDate);
	
	// startDate , endDate
	
	List<ProductSoldDTO> getProductSold(LocalDate startDate, LocalDate endDate);
	
	List<ExpenseDTO> getExpense(LocalDate startDate, LocalDate endDate);
}
