package com.insightsoftware.brewer.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.insightsoftware.brewer.model.Usuario;

public interface UsuarioRepositoryQueries {

  public Optional<Usuario> porEmaileAtivo(String email);

  public List<String> permissoes(Usuario usuario);

}
