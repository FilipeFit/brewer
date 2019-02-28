package com.insightsoftware.brewer.repository.helper.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.insightsoftware.brewer.model.Cidade;
import com.insightsoftware.brewer.repository.filter.CidadeFilter;

public interface CidadeRepositoryQueries {
  public Page<Cidade> filtrar(CidadeFilter filter, Pageable pageable);
}
