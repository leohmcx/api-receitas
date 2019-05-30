package br.com.desafio.api.receita.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import br.com.desafio.api.receita.domain.entity.Ingrediente;

@RestResource(exported = false)
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}