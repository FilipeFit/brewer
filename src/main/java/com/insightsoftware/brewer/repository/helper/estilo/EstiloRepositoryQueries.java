package com.insightsoftware.brewer.repository.helper.estilo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.insightsoftware.brewer.model.Estilo;
import com.insightsoftware.brewer.repository.filter.EstiloFilter;

public interface EstiloRepositoryQueries {

  public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable);

}
