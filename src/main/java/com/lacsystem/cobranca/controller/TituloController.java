package com.lacsystem.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lacsystem.cobranca.model.StatusTitulo;
import com.lacsystem.cobranca.model.Titulo;
import com.lacsystem.cobranca.repository.Titulos;

/**
 * @author Luiz.Cesario
 *
 */

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private Titulos titulos;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		titulos.save(titulo);
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Título salvo com sucesso!");
		return mv;
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> listaTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos",listaTitulos);
		return mv;
	}
	
	@ModelAttribute("carregarStatusTitulo")
	public List<StatusTitulo> carregarStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}

}
