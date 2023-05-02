package com.project.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.phoneshop.model.Brand;

//Database Layer
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
