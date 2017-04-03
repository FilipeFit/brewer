package com.insightsoftware.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insightsoftware.brewer.model.Cerveja;
import com.insightsoftware.brewer.repository.CervejaRepository;
import com.insightsoftware.brewer.service.event.cerveja.CervejaSalvaEvent;

@Service
public class CadastroCervejaService {

	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher; 

	@Transactional
	public void salvar(Cerveja cerveja) {

		cervejaRepository.save(cerveja);
		
		publisher.publishEvent(new CervejaSalvaEvent(cerveja));

	}

}