package com.lacsystem.cobranca.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lacsystem.cobranca.model.StatusTitulo;
import com.lacsystem.cobranca.model.Titulo;
import com.lacsystem.cobranca.repository.Titulos;
import com.lacsystem.cobranca.repository.filter.TituloFilter;

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
	
	public List<Titulo> filtrar(TituloFilter filtro){
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		List<Titulo> listaTitulos = titulos.findByDescricaoContaining(descricao);
		return listaTitulos.stream().sorted((a,b) -> a.getId().compareTo(b.getId()))
								 .collect(Collectors.toList());
	}
	
}
