package br.com.desafio.api.receita.domain.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Receita")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty(value = "id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id")
	@JsonBackReference(value = "categoria-receita")
	private Categoria categoria;

	@Column(name = "tempo_preparo")
	@JsonProperty(value = "tempo_preparo")
	private int tempoPreparo;

	@Column(name = "rendimento")
	@JsonProperty(value = "rendimento")
	private int rendimento;

	@Column(name = "modo_preparo")
	@JsonProperty(value = "modo_preparo")
	private String modoPreparo;

	@OneToMany(mappedBy = "itensReceita", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	// @JsonIgnore
	@JsonManagedReference(value = "receita-itens")
	private List<Itens> ingredientes = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getTempoPreparo() {
		return tempoPreparo;
	}

	public void setTempoPreparo(int tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}

	public int getRendimento() {
		return rendimento;
	}

	public void setRendimento(int rendimento) {
		this.rendimento = rendimento;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

	public void addIngrediente(Ingrediente ingrediente, int qtdeItem) {
		Itens itens = new Itens(this, ingrediente, qtdeItem);
		this.ingredientes.add(itens);
		ingrediente.getReceitas().add(itens);
	}

	public boolean hasIngrediente(Ingrediente ingrediente) {
		for (Iterator<Itens> iterator = this.ingredientes.iterator(); iterator.hasNext();) {
			Itens itens = iterator.next();

			if (itens.getItensReceita().equals(this) && itens.getItensIngrediente().equals(ingrediente)) {
				return true;
			}
		}
		return false;
	}

	public void removeIngrediente(Ingrediente produto) {
		for (Iterator<Itens> iterator = this.ingredientes.iterator(); iterator.hasNext();) {
			Itens itens = iterator.next();

			if (itens.getItensReceita().equals(this) && itens.getItensIngrediente().equals(produto)) {
				iterator.remove();
				itens.getItensIngrediente().getReceitas().remove(itens);
				itens.setItensReceita(null);
				itens.setItensIngrediente(null);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Receita that = (Receita) o;
		return Objects.equals(tempoPreparo, that.tempoPreparo) && Objects.equals(rendimento, that.rendimento)
				&& Objects.equals(modoPreparo, that.modoPreparo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tempoPreparo, rendimento, modoPreparo);
	}

}
