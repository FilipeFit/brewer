package com.insightsoftware.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insightsoftware.brewer.model.Cliente;
import com.insightsoftware.brewer.repository.ClienteRepository;
import com.insightsoftware.brewer.service.exception.CpfCnpjClienteJaCadastradoException;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public void salvar(Cliente cliente) {
		Optional<Cliente> clienteExistente = clienteRepository.findByCpfOuCnpj(cliente.getCpfOuCnpjSemFormatacao());

		if (clienteExistente.isPresent()) {
			throw new CpfCnpjClienteJaCadastradoException("CPF ou CNPJ já cadastrado");
		}

		clienteRepository.save(cliente);
	}

}
