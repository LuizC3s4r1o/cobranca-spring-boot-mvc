package com.lacsystem.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lacsystem.cobranca.model.Titulo;

/**
 * @author Luiz.Cesario
 *
 */

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Titulo titulo) {
		
		//TODO: salvar no bd
		System.out.println("titulo:" + titulo.getDescricao());
		
		return "CadastroTitulo";
	}

}
