package com.insightsoftware.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insightsoftware.brewer.model.Cidade;
import com.insightsoftware.brewer.model.Estado;
import com.insightsoftware.brewer.repository.helper.cidade.CidadeRepositoryQueries;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>, CidadeRepositoryQueries {

	public List<Cidade> findByEstadoCodigo(Long codigoEstado);
	
	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);

}
