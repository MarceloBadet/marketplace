package com.badet.marketplace.api.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class AtualizarProdutoDto extends CadastroProdutoDto {
	
	private String id;
	
	@NotEmpty(message = "O identificador do produto não pode ser vazio.")
	@Min(value = 1L, message = "Valor do identificador do produto está inválido.")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AtualizarProdutoDto [id=" + id + "]";
	}

	
}
