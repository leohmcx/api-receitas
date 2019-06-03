package br.com.desafio.api.receita.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.api.receita.domain.dao.CategoriaRepository;
import br.com.desafio.api.receita.domain.dao.IngredienteRepository;
import br.com.desafio.api.receita.domain.dao.ReceitaRepository;
import br.com.desafio.api.receita.domain.entity.Categoria;
import br.com.desafio.api.receita.domain.entity.Ingrediente;
import br.com.desafio.api.receita.domain.entity.Receita;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	public Iterable<Receita> findByNomeContaining(String nome) {
		return receitaRepository.findByNomeContaining(nome);
	}
	
	public Iterable<Receita> findByCategoria(String nome) {
		Categoria categoria = categoriaRepository.findByNomeContaining(nome);
		if(categoria == null) {
			return null;
		}
		return receitaRepository.findByCategoria_id(categoria.getId());
	}
	
	public Iterable<Receita> findByIngrediente(String nome) {
		return receitaRepository.findReceitaByIngredienteNome(nome);
	}

	public Receita add(Receita receita) {
		Categoria categoria = categoriaRepository.findByNomeContaining(receita.getCategoria().getNome());		
		if (categoria == null) {
			categoria = new Categoria();
			categoria.setNome(receita.getCategoria().getNome());
			categoriaRepository.save(categoria);
		}
		receita.setCategoria(categoria);
		return receitaRepository.save(receita);
	}

	public void delete(long id) {
		if (receitaRepository.exists(id)) {
			receitaRepository.delete(id);
		}
	}

	public Receita update(long id, Receita receita, String ingrediente, String qtde, String medida) {
		
		List<Ingrediente> ingredientes = new ArrayList<>();
		HashMap<Long, String> controleQuantidade = new HashMap<>();
		HashMap<Long, String> controleMedida = new HashMap<>();		
		
		if (receitaRepository.exists(id)) {
			if (ingrediente == null && qtde == null && medida == null) {
				
				ingredientes = ingredienteRepository.findIngredientesByReceitaId(id);
				controleQuantidade = buscaQtdeItem(id, ingredientes);
				controleMedida = buscaMedidaItem(id, ingredientes);
				addAndRemoveItem(id, ingredientes, controleQuantidade, controleMedida);
				
				Categoria categoria = categoriaRepository.findByNomeContaining(receita.getCategoria().getNome());
				
				if (categoria == null) {
					categoria = new Categoria();
					categoria.setNome(receita.getCategoria().getNome());
					categoria = categoriaRepository.save(categoria);
				}

				receita.setId(id);
				receitaRepository.save(receita);

				addAndRemoveItem(id, ingredientes, controleQuantidade, controleMedida);
				receita.setCategoria(categoria);
			} else {
				Ingrediente i = ingredienteRepository.findByNomeContaining(ingrediente);
				
				if(i == null) {
					i = new Ingrediente();
					i.setNome(ingrediente);
					i = ingredienteRepository.save(i);
				}
				
				ingredientes.add(i);
				controleQuantidade.put(i.getId(), qtde);
				controleMedida.put(i.getId(), medida);															
				addAndRemoveItem(id, ingredientes, controleQuantidade, controleMedida);
			}
		}
		return receitaRepository.findOne(id);
	}

	private HashMap<Long, String> buscaQtdeItem(Long id, List<Ingrediente> itens) {
		HashMap<Long, String> controle = new HashMap<>();
		for (Ingrediente item : itens) {
			controle.put(item.getId(), receitaRepository.findQtdeItem(id, item.getId()));
		}
		return controle;
	}

	private HashMap<Long, String> buscaMedidaItem(Long id, List<Ingrediente> itens) {
		HashMap<Long, String> controle = new HashMap<>();
		for (Ingrediente item : itens) {
			controle.put(item.getId(), receitaRepository.findMedidaItem(id, item.getId()));
		}
		return controle;
	}

	private void addAndRemoveItem(Long id, List<Ingrediente> itens, HashMap<Long, String> qtde, HashMap<Long, String> med) {
		Receita receita = receitaRepository.findOne(id);
		for (Ingrediente item : itens) {
			if (item != null) {
				if (receita.hasIngrediente(item)) {
					receita.removeIngrediente(item);
				} else {
					if (!receita.hasIngrediente(item)) {
						receita.addIngrediente(item, qtde.get(item.getId()), med.get(item.getId()));
					}
				}
				receitaRepository.flush();
			}
		}
	}
}
