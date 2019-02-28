package com.insightsoftware.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insightsoftware.brewer.model.Usuario;
import com.insightsoftware.brewer.repository.helper.usuario.UsuarioRepositoryQueries;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQueries {

  public Optional<Usuario> findByEmailIgnoreCase(String email);
}
