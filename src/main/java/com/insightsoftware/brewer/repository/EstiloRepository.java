package com.insightsoftware.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insightsoftware.brewer.model.Estilo;
import com.insightsoftware.brewer.repository.helper.estilo.EstiloRepositoryQueries;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Long>, EstiloRepositoryQueries {

  public Optional<Estilo> findByNomeIgnoreCase(String nome);

}
