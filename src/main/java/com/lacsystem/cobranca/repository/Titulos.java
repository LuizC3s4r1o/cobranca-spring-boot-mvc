package com.lacsystem.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lacsystem.cobranca.model.Titulo;

/**
 * @author Luiz.Cesario
 *
 */
public interface Titulos extends JpaRepository<Titulo, Long>{

	public List<Titulo> findByDescricaoContaining(String descricao);
}
