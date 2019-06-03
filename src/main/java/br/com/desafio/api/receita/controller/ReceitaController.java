package br.com.desafio.api.receita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.api.receita.domain.entity.Receita;
import br.com.desafio.api.receita.service.ReceitaService;
import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping(path = "/api/v1/receita")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;
	
	@GetMapping(path = "/{nome}", produces = "application/json")
	public Iterable<Receita> findByNomeContaining(@PathVariable("nome") String nome) {
		return receitaService.findByNomeContaining(nome);
	}
	
	@GetMapping(path = "/categoria/{nome}", produces = "application/json")
	public Iterable<Receita> findByCategoria(@PathVariable("nome") String nome) {
		return receitaService.findByCategoria(nome);
	}
	
	@GetMapping(path = "/ingrediente/{nome}", produces = "application/json")
	public Iterable<Receita> findByIngrediente(@PathVariable("nome") String nome) {
		return receitaService.findByIngrediente(nome);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public Receita add(@RequestBody Receita receita) {
		return receitaService.add(receita);
	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") long id) {
		receitaService.delete(id);
	}
	
	@PutMapping(path = "/{id}", consumes = "application/json")
	public Receita update(@PathVariable("id") long id
			, @RequestBody Receita receita) throws BadHttpRequest {
		return receitaService.update(id, receita, null, null, null);		
	}
	
	@PatchMapping(path = "/{id}/{ingrediente}/{qtde}/{medida}")
	public Receita addIngrediente(@PathVariable("id") long id
			, @PathVariable("ingrediente") String ingrediente
			, @PathVariable("qtde") String qtde
			, @PathVariable("medida") String medida) throws BadHttpRequest {
		return receitaService.update(id, null, ingrediente, qtde, medida);		
	}
}
