package com.project.phoneshop.dto;

import lombok.Data;

@Data
public class ProductSoldDTO {
	private Long productId;

	private String productName;

	private Integer totalUnit;

	private Double amount;
}
