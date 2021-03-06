package com.insightsoftware.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.insightsoftware.brewer.controller.page.PageWrapper;
import com.insightsoftware.brewer.model.Cerveja;
import com.insightsoftware.brewer.model.Origem;
import com.insightsoftware.brewer.model.Sabor;
import com.insightsoftware.brewer.repository.CervejaRepository;
import com.insightsoftware.brewer.repository.EstiloRepository;
import com.insightsoftware.brewer.repository.filter.CervejaFilter;
import com.insightsoftware.brewer.service.CadastroCervejaService;

@Controller
@RequestMapping("/cervejas")
public class CervejasController {

  private final EstiloRepository estiloRepository;
  private final CadastroCervejaService cadastroCervejaService;
  private final CervejaRepository cervejaRepository;

  @Autowired
  public CervejasController(EstiloRepository estiloRepository,
      CadastroCervejaService cadastroCervejaService,
      CervejaRepository cervejaRepository) {
    this.estiloRepository = estiloRepository;
    this.cadastroCervejaService = cadastroCervejaService;
    this.cervejaRepository = cervejaRepository;
  }

  @RequestMapping("/novo")
  public ModelAndView novo(Cerveja cerveja) {

    ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
    mv.addObject("sabores", Sabor.values());
    mv.addObject("estilos", estiloRepository.findAll());
    mv.addObject("origens", Origem.values());

    return mv;
  }

  @RequestMapping(value = "/novo", method = RequestMethod.POST)
  public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model,
      RedirectAttributes attributes) {

    if (result.hasErrors()) {
      return novo(cerveja);
    }

    cadastroCervejaService.salvar(cerveja);
    attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso");
    return new ModelAndView("redirect:/cervejas/novo");
  }

  @GetMapping
  public ModelAndView pesquisar(CervejaFilter cervejaFilter, BindingResult result,
      @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
    ModelAndView mv = new ModelAndView("cerveja/PesquisaCervejas");
    mv.addObject("estilos", estiloRepository.findAll());
    mv.addObject("sabores", Sabor.values());
    mv.addObject("origens", Origem.values());

    PageWrapper<Cerveja> paginaWrapper =
        new PageWrapper<>(cervejaRepository.filtrar(cervejaFilter, pageable), httpServletRequest);
    mv.addObject("pagina", paginaWrapper);
    return mv;
  }

}
