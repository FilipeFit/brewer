package com.insightsoftware.brewer.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insightsoftware.brewer.model.Usuario;
import com.insightsoftware.brewer.repository.UsuarioRepository;

@Service
public class AppUserDetailService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  @Autowired
  public AppUserDetailService(
      UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Optional<Usuario> usuarioOptional = usuarioRepository.porEmaileAtivo(email);
    Usuario usuario = usuarioOptional.orElseThrow(
        () -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
    return new User(usuario.getEmail(), usuario.getSenha(), getPermissoes(usuario));
  }

  private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();

    List<String> permissoes = usuarioRepository.permissoes(usuario);
    permissoes.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.toUpperCase())));

    return authorities;
  }

}
