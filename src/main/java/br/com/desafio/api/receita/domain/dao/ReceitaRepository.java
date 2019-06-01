package br.com.desafio.api.receita.domain.dao;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import br.com.desafio.api.receita.domain.entity.Receita;

@RestResource(exported = false)
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
	
	List<Receita> findByNomeContaining(@PathParam("nome") String nome);
	
	List<Receita> findByCategoria_id(@PathParam("id") Long id);
	
	@Query(value = "SELECT r.* "
			+ "FROM receita r"
			+ ", ingrediente p"
			+ ", itens i "
			+ "WHERE p.id = i.itens_ingrediente_id "
			+ "AND r.id = i.itens_receita_id "
			+ "AND p.id = :pId", nativeQuery = true)
	List<Receita> findReceitaByIngredienteId(@Param("pId") Long id);
	
	@Query(value = "SELECT r.* "
			+ "FROM receita r"
			+ ", ingrediente p"
			+ ", itens i "
			+ "WHERE p.id = i.itens_ingrediente_id "
			+ "AND r.id = i.itens_receita_id "
			+ "AND p.nome like CONCAT(:pNome,'%')", nativeQuery = true)
	List<Receita> findReceitaByIngredienteNome(@Param("pNome") String nome);
	
	@Query(value = "SELECT i.qtde_item "
			+ "FROM receita r"
			+ ", ingrediente p"
			+ ", itens i "
			+ "WHERE p.id = i.itens_ingrediente_id "
			+ "AND r.id = i.itens_receita_id "
			+ "AND r.id = :pId "
			+ "AND p.id = :pIngredienteId", nativeQuery = true)
	String findQtdeItem(@Param("pId") Long id, @Param("pIngredienteId") Long ingredienteId);
	
	@Query(value = "SELECT i.medida "
			+ "FROM receita r"
			+ ", ingrediente p"
			+ ", itens i "
			+ "WHERE p.id = i.itens_ingrediente_id "
			+ "AND r.id = i.itens_receita_id "
			+ "AND r.id = :pId "
			+ "AND p.id = :pIngredienteId", nativeQuery = true)
	String findMedidaItem(@Param("pId") Long id, @Param("pIngredienteId") Long ingredienteId);
}