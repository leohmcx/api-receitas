package br.com.desafio.api.receita.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Receita")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty(value = "id")
	private long id;

	@OneToMany(mappedBy = "itensIngrediente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference(value = "ingrediente-itens")
	private List<Itens> receitas = new ArrayList<>();

	@Column(name = "nome")
	@JsonProperty(value = "nome")
	private String nome;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Itens> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Itens> receitas) {
		this.receitas = receitas;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Ingrediente that = (Ingrediente) o;
		return Objects.equals(nome, that.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
}
