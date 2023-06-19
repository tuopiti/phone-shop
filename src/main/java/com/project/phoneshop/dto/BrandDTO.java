package com.project.phoneshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {
	private Long id;
	private String name;	
	private Boolean active;
	
	
}
