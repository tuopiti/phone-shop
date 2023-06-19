package com.project.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.phoneshop.model.ProductImportHistory;

public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long>{

}
