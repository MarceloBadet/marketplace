package com.badet.marketplace.api.dtos;

import java.util.Date;
import java.util.List;

public class RetornoConsultaPorNomeDto {

	private String termoPesquisado;
	private Date dataAtual;
	private List<ProdutoConsultaDto> listaProduto;

	public String getTermoPesquisado() {
		return termoPesquisado;
	}
	public void setTermoPesquisado(String termoPesquisado) {
		this.termoPesquisado = termoPesquisado;
	}

	public Date getDataAtual() {
		return dataAtual;
	}
	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public List<ProdutoConsultaDto> getListaProduto() {
		return listaProduto;
	}
	public void setListaProduto(List<ProdutoConsultaDto> listaProduto) {
		this.listaProduto = listaProduto;
	}
}
