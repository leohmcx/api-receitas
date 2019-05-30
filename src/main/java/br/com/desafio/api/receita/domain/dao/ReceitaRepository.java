package br.com.desafio.api.receita.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import br.com.desafio.api.receita.domain.entity.Receita;

@RestResource(exported = false)
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}