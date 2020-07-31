package com.badet.marketplace.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itemVenda")
public class ItemVenda implements Serializable  {

	private static final long serialVersionUID = -4952782465178412500L;

	private Long id;
	private Venda venda;
	private Produto produto;
	private Integer quantidade;
	private Integer avaliacao;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idItemVenda")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "idVenda")
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@ManyToOne
	@JoinColumn(name = "idProduto")
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Column(name = "quantidade", nullable = false)
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Column(name = "avaliacao", nullable = false)
	public Integer getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	@Override
	public String toString() {
		return "ItemVenda [id=" + id + ", venda=" + venda + ", produto=" + produto + ", quantidade=" + quantidade
				+ ", avaliacao=" + avaliacao + "]";
	}
}
