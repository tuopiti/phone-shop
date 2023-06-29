package com.project.phoneshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "models")
public class Model {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@GeneratedValue(generator = "model_seq_generator")
	@SequenceGenerator(name = "model_seq_generator", initialValue = 1, sequenceName = "model_seq")
	private Long id;
	
	//@Column(name = "model_name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "the_brand_id")
	private Brand brand;
	
	@NotNull(message = "{required.field}")
	@Column(name = "year_made")
	private Short yearMade;
}
