package com.insightsoftware.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insightsoftware.brewer.model.Estilo;
import com.insightsoftware.brewer.repository.EstiloRepository;
import com.insightsoftware.brewer.service.exception.NomeEstiloJaCadastradoException;

@Service
public class CadastroEstiloService {

  private final EstiloRepository estiloRepository;

  public CadastroEstiloService(
      EstiloRepository estiloRepository) {
    this.estiloRepository = estiloRepository;
  }

  @Transactional
  public Estilo salvar(Estilo estilo) {

    Optional<Estilo> estiloOptional = estiloRepository.findByNomeIgnoreCase(estilo.getNome());
    if (estiloOptional.isPresent()) {
      throw new NomeEstiloJaCadastradoException("Nome do estilo já cadastrado");
    }

    return estiloRepository.saveAndFlush(estilo);
  }

}
