package com.insightsoftware.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insightsoftware.brewer.model.Cliente;
import com.insightsoftware.brewer.repository.helper.cliente.ClienteRepositoryQueries;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQueries {

	public Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
