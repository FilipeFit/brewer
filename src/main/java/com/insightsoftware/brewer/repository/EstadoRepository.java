package com.insightsoftware.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insightsoftware.brewer.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
	

}
