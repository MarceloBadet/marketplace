package com.badet.marketplace.api.dtos;

public class ProdutoDto extends ProdutoConsultaDto {

	private CategoriaDto categoria;

	public CategoriaDto getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaDto categoria) {
		this.categoria = categoria;
	}
}
