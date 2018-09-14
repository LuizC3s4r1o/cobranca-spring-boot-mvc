package com.lacsystem.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Luiz.Cesario
 *
 */

@Controller
public class TituloController {
	
	@RequestMapping("/titulos/novo")
	public String novo() {
		return "CadastroTitulo";
	}

}
