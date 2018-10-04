package com.lacsystem.cobranca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lacsystem.cobranca.model.StatusTitulo;
import com.lacsystem.cobranca.model.Titulo;
import com.lacsystem.cobranca.repository.Titulos;

/**
 * @author Luiz.Cesario
 *
 */

@Service
public class CadastroTituloService {

	@Autowired
	private Titulos titulos;
	
	public void salvar(Titulo titulo) {
		titulos.save(titulo);
	}

	public void excluir(Long id) {
		titulos.deleteById(id);
	}

	public String receber(Long id) {
		Optional<Titulo> titulo = titulos.findById(id);
		titulo.get().setStatus(StatusTitulo.RECEBIDO);
		this.salvar(titulo.get());
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}
}
