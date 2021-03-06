package com.insightsoftware.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.insightsoftware.brewer.controller.page.PageWrapper;
import com.insightsoftware.brewer.model.Cliente;
import com.insightsoftware.brewer.model.TipoPessoa;
import com.insightsoftware.brewer.repository.ClienteRepository;
import com.insightsoftware.brewer.repository.EstadoRepository;
import com.insightsoftware.brewer.repository.filter.ClienteFilter;
import com.insightsoftware.brewer.service.CadastroClienteService;
import com.insightsoftware.brewer.service.exception.CpfCnpjClienteJaCadastradoException;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

  private final EstadoRepository estadoRepository;
  private final CadastroClienteService cadastroClienteService;
  private final ClienteRepository clienteRepository;

  @Autowired
  public ClientesController(EstadoRepository estadoRepository,
      CadastroClienteService cadastroClienteService,
      ClienteRepository clienteRepository) {
    this.estadoRepository = estadoRepository;
    this.cadastroClienteService = cadastroClienteService;
    this.clienteRepository = clienteRepository;
  }

  @RequestMapping("/novo")
  public ModelAndView novo(Cliente cliente) {
    ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
    mv.addObject("tiposPessoa", TipoPessoa.values());
    mv.addObject("estados", estadoRepository.findAll());
    return mv;
  }

  @PostMapping("/novo")
  public ModelAndView salvar(@Valid Cliente cliente, BindingResult result,
      RedirectAttributes attributes) {
    if (result.hasErrors()) {
      return novo(cliente);
    }

    try {
      cadastroClienteService.salvar(cliente);
    } catch (CpfCnpjClienteJaCadastradoException e) {
      result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
      return novo(cliente);
    }

    attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
    return new ModelAndView("redirect:/clientes/novo");
  }

  @GetMapping
  public ModelAndView pesquisar(ClienteFilter filter, BindingResult result,
      @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
    ModelAndView mv = new ModelAndView("cliente/PesquisaClientes");

    PageWrapper<Cliente> pageWrapper =
        new PageWrapper<>(clienteRepository.filtrar(filter, pageable), httpServletRequest);
    mv.addObject("pagina", pageWrapper);
    return mv;
  }

}
