package com.project.phoneshop.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {
	@CreatedDate
	@Column(name = "date_created")
	private LocalDateTime dateCreated;
	
	@LastModifiedDate
	@Column(name = "date_updated")
	private LocalDateTime dateUpdated;
	
	@CreatedBy
	@Column(name = "created_user")
	private String createdUser;
	
	@LastModifiedBy
	@Column(name = "updated_user")
	private String updatedUser;
}
