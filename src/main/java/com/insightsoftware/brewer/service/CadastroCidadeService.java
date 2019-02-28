package com.insightsoftware.brewer.service;

import com.insightsoftware.brewer.model.Cidade;
import com.insightsoftware.brewer.repository.CidadeRepository;
import com.insightsoftware.brewer.service.exception.CidadeJaExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroCidadeService {

  private final CidadeRepository cidadeRepository;

  @Autowired
  public CadastroCidadeService(
      CidadeRepository cidadeRepository) {
    this.cidadeRepository = cidadeRepository;
  }

  @Transactional
  public void salvar(Cidade cidade) {

    Optional<Cidade> cidadeExistente =
        cidadeRepository.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());

    if (cidadeExistente.isPresent()) {
      throw new CidadeJaExistenteException("Cidade já cadastrada para este estado");
    }

    cidadeRepository.save(cidade);

  }

}
