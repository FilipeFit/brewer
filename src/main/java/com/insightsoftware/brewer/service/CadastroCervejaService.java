package com.insightsoftware.brewer.service;

import com.insightsoftware.brewer.model.Cerveja;
import com.insightsoftware.brewer.repository.CervejaRepository;
import com.insightsoftware.brewer.service.event.cerveja.CervejaSalvaEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCervejaService {

  private final CervejaRepository cervejaRepository;
  private final ApplicationEventPublisher publisher;

  @Autowired
  public CadastroCervejaService(
      CervejaRepository cervejaRepository,
      ApplicationEventPublisher publisher) {
    this.cervejaRepository = cervejaRepository;
    this.publisher = publisher;
  }

  @Transactional
  public void salvar(Cerveja cerveja) {

    cervejaRepository.save(cerveja);

    publisher.publishEvent(new CervejaSalvaEvent(cerveja));

  }

}
