package com.insightsoftware.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insightsoftware.brewer.model.Usuario;
import com.insightsoftware.brewer.repository.UsuarioRepository;
import com.insightsoftware.brewer.service.exception.EmailCadastradoException;
import com.insightsoftware.brewer.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class CadastroUsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public CadastroUsuarioService(
      UsuarioRepository usuarioRepository,
      PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public Usuario salvar(Usuario usuario) {
    Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailIgnoreCase(usuario.getEmail());

    if (usuarioOptional.isPresent()) {
      throw new EmailCadastradoException("Usuário com este email já foi cadastrado");
    }

    if (usuario.isNovo() && usuario.getSenha().isEmpty()) {
      throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para um novo usuário");
    }

    if (usuario.isNovo()) {
      usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
      usuario.setConfirmacaoSenha(usuario.getSenha());
    }

    return usuarioRepository.saveAndFlush(usuario);
  }


}
