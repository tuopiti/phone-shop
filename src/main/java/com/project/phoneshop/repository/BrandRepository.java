package com.project.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.phoneshop.model.Brand;

//Database Layer
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);
	
	List<Brand> findByIdIn(List<Long> ids);
	
	//List<Brand> findByActive(boolean isActive);
	List<Brand> findByActiveTrue();

}
