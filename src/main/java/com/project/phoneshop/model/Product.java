package com.project.phoneshop.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "products", 
uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id", "color_id"})})
@Data
public class Product extends AuditEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "{cannot.be.blank}")
	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id")
	private Model model;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	@Column(name = "sale_price")
	private BigDecimal salePrice;

	@Column(name = "available_unit")
	private Integer availableUnit; // @TODO should move to Stock table? reason?

	@Column(name = "image_path")
	private String imagePath;
}
