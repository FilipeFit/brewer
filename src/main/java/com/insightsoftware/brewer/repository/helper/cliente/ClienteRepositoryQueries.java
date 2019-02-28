package com.insightsoftware.brewer.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.insightsoftware.brewer.model.Cliente;
import com.insightsoftware.brewer.repository.filter.ClienteFilter;

public interface ClienteRepositoryQueries {

  public Page<Cliente> filtrar(ClienteFilter filter, Pageable pageable);

}
