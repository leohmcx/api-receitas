package br.com.desafio.api.receita.domain.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "itens")
public class Itens {

	@EmbeddedId
	private ItensId id;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("itensReceitaId")
	@JsonBackReference(value = "receita-itens")
	private Receita itensReceita;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("itensIngredienteId")
	@JsonIgnore
	@JsonManagedReference(value = "ingrediente-itens")
	private Ingrediente itensIngrediente;

	@Column(name = "qtde_item")
	@JsonProperty(value = "qtde_item")
	private String qtdeItem;
	
	@Column(name = "medida")
	@JsonProperty(value = "medida")
	private String medida;

	public Itens(Receita itensReceita, Ingrediente itensIngrediente, String qtdeItem, String medida) {
		this.itensReceita = itensReceita;
		this.itensIngrediente = itensIngrediente;
		this.qtdeItem = qtdeItem;
		this.medida = medida;
		this.id = new ItensId(itensReceita.getId(), itensIngrediente.getId());
	}

	public Itens() {
	}

	public String getQtdeItem() {
		return qtdeItem;
	}

	public void setQtdeItem(String qtdeItem) {
		this.qtdeItem = qtdeItem;
	}

	public Receita getItensReceita() {
		return itensReceita;
	}

	public void setItensReceita(Receita itensReceita) {
		this.itensReceita = itensReceita;
	}

	public Ingrediente getItensIngrediente() {
		return itensIngrediente;
	}

	public void setItensIngrediente(Ingrediente itensIngrediente) {
		this.itensIngrediente = itensIngrediente;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Itens that = (Itens) o;
		return Objects.equals(itensReceita, that.itensReceita)
				&& Objects.equals(itensIngrediente, that.itensIngrediente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(itensReceita, itensIngrediente);
	}
}
