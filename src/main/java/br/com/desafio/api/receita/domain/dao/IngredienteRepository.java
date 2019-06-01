package br.com.desafio.api.receita.domain.dao;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import br.com.desafio.api.receita.domain.entity.Ingrediente;

@RestResource(exported = false)
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {	
	Ingrediente findByNomeContaining(@PathParam("nome") String nome);
	
	@Query(value = "SELECT p.* "
			+ "FROM receita r"
			+ ", ingrediente p"
			+ ", itens i "
			+ "WHERE p.id = i.itens_ingrediente_id "
			+ "AND r.id = i.itens_receita_id "
			+ "AND r.id = :pId", nativeQuery = true)
	List<Ingrediente> findIngredientesByReceitaId(@Param("pId") Long id);	
}