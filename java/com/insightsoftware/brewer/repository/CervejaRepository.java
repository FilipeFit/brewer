package com.insightsoftware.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insightsoftware.brewer.model.Cerveja;
import com.insightsoftware.brewer.repository.helper.cerveja.CervejaRepositoryQueries;

@Repository
public interface CervejaRepository extends JpaRepository<Cerveja, Long>, CervejaRepositoryQueries {
	
	public Optional<Cerveja> findBySkuIgnoreCase(String sku);

}
