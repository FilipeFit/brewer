package com.insightsoftware.brewer.repository.helper.cerveja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.insightsoftware.brewer.model.Cerveja;
import com.insightsoftware.brewer.repository.filter.CervejaFilter;

public interface CervejaRepositoryQueries {
	
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);

}
