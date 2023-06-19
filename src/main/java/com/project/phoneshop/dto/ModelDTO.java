package com.project.phoneshop.dto;

import lombok.Data;

@Data
public class ModelDTO {
	private Long id;
	private String name;
	private Integer brandId;
}

/*
@Data
public class ModelDTO {
	private Long id;
	private String name;	
	//private BrandDTO brand;	
	private BrandDTO brandDTO;
	
}*/
