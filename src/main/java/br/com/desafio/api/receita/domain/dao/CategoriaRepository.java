package br.com.desafio.api.receita.domain.dao;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import br.com.desafio.api.receita.domain.entity.Categoria;

@RestResource(exported = false)
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	Categoria findByNomeContaining(@PathParam("nome") String nome);
}
