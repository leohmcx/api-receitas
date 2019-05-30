package br.com.desafio.api.receita.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItensId implements Serializable {
	private static final long serialVersionUID = 2002671496088694852L;

	@Column(name = "itens_receita_id")
	private Long itensReceitaId;

	@Column(name = "itens_ingrediente_id")
	private Long itensIngredienteId;

	public ItensId() {}

	public ItensId(Long receitaId, Long ingredienteId) {
		this.itensReceitaId = receitaId;
		this.itensIngredienteId = ingredienteId;
	}
	
	public Long getItensReceitaId() {
		return itensReceitaId;
	}

	public void setItensReceitaId(Long itensReceitaId) {
		this.itensReceitaId = itensReceitaId;
	}

	public Long getItensIngredienteId() {
		return itensIngredienteId;
	}

	public void setItensIngredienteId(Long itensIngredienteId) {
		this.itensIngredienteId = itensIngredienteId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		ItensId that = (ItensId) o;
		return Objects.equals(itensReceitaId, that.itensReceitaId)
				&& Objects.equals(itensIngredienteId, that.itensIngredienteId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(itensReceitaId, itensIngredienteId);
	}
}
