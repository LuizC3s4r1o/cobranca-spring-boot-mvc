package com.lacsystem.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String novo() {
		return "CadastroTitulo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Titulo titulo) {
		
		
		System.out.println("titulo:" + titulo.getDescricao());
		titulos.save(titulo);
		return "CadastroTitulo";
	}

}
