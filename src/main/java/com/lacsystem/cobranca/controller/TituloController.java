package com.lacsystem.cobranca.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lacsystem.cobranca.model.StatusTitulo;
import com.lacsystem.cobranca.model.Titulo;
import com.lacsystem.cobranca.repository.Titulos;
import com.lacsystem.cobranca.service.CadastroTituloService;

/**
 * @author Luiz.Cesario
 *
 */

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private Titulos titulos;
	
	@Autowired
	private CadastroTituloService cadastroTituloService;
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes atributes) {
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		cadastroTituloService.salvar(titulo);
		atributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		return "redirect:/titulos/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> listaTitulos = titulos.findAll().stream()
												.sorted((a,b) -> a.getId().compareTo(b.getId()))
												.collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos",listaTitulos);
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Titulo titulo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable("id") Long id, RedirectAttributes atributes) {
		cadastroTituloService.excluir(id);
		atributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulos";
	}
	
	@RequestMapping(value="{id}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable("id") Long id) {
		return cadastroTituloService.receber(id);
	}
	
	@ModelAttribute("carregarStatusTitulo")
	public List<StatusTitulo> carregarStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}

}
