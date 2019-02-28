package com.insightsoftware.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.insightsoftware.brewer.model.Usuario;
import com.insightsoftware.brewer.repository.GrupoRepository;
import com.insightsoftware.brewer.service.CadastroUsuarioService;
import com.insightsoftware.brewer.service.exception.EmailCadastradoException;
import com.insightsoftware.brewer.service.exception.SenhaObrigatoriaUsuarioException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

  private final CadastroUsuarioService cadastroUsuarioService;
  private final GrupoRepository grupoRepository;

  @Autowired
  public UsuariosController(
      CadastroUsuarioService cadastroUsuarioService,
      GrupoRepository grupoRepository) {
    this.cadastroUsuarioService = cadastroUsuarioService;
    this.grupoRepository = grupoRepository;
  }

  @RequestMapping("/novo")
  public ModelAndView novo(Usuario usuario) {
    ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
    mv.addObject("grupos", grupoRepository.findAll());
    return mv;
  }

  @PostMapping("/novo")
  public ModelAndView salvar(@Valid Usuario usuario, BindingResult result,
      RedirectAttributes attributes) {

    if (result.hasErrors()) {
      return novo(usuario);
    }

    try {
      cadastroUsuarioService.salvar(usuario);
    } catch (EmailCadastradoException e) {
      result.rejectValue("email", e.getMessage(), e.getMessage());
      return novo(usuario);
    } catch (SenhaObrigatoriaUsuarioException e) {
      result.rejectValue("senha", e.getMessage(), e.getMessage());
      return novo(usuario);
    }

    attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
    return new ModelAndView("redirect:/usuarios/novo");
  }

}
