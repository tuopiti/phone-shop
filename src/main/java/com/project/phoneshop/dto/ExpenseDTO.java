package com.project.phoneshop.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ExpenseDTO {
	private Long productId;
	private String productName;
	private Integer quantity;
	private BigDecimal amount;
}
