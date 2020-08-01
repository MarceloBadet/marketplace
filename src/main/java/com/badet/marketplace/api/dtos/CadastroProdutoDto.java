package com.badet.marketplace.api.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CadastroProdutoDto {

	private String nome;
	private String descricao;
	private String idCategoria;
	
	@NotEmpty(message = "O nome do produto não pode ser vazio")
	@Length(min = 3, max = 255, message = "Nome do produto deve conter entre 3 e 255 caracteres.")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message = "A descrição do produto não pode ser vazio")
	@Length(min = 20, max = 255, message = "A descrição do produto deve conter entre 20 e 1000 caracteres.")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@NotEmpty(message = "A categoria do produto não pode ser vazia.")
	@Min(value = 1L, message = "O valor da categoria está inválido.")
	public String getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public String toString() {
		return "CadastroProdutoDto [nome=" + nome + ", descricao=" + descricao + ", idCategoria=" + idCategoria + "]";
	}
}
