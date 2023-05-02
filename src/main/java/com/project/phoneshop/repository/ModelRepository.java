package com.project.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.project.phoneshop.model.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> , JpaSpecificationExecutor<Model> {

}
