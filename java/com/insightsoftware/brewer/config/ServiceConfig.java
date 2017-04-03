package com.insightsoftware.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.insightsoftware.brewer.service.CadastroCervejaService;
import com.insightsoftware.brewer.storage.FotoStorage;
import com.insightsoftware.brewer.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroCervejaService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorageLocal() {
		return new FotoStorageLocal();
	}

}
