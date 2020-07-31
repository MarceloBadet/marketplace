package com.badet.marketplace.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda implements Serializable  {

	private static final long serialVersionUID = 2789721395393808515L;

	private Long id;
	private Vendedor vendedor;
	private Comprador comprador;
	private Date dataVenda;
	private List<ItemVenda> listaItens;
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "idVendedor")
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	@ManyToOne
	@JoinColumn(name = "idComprador")
	public Comprador getComprador() {
		return comprador;
	}
	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	@Column(name="dataVenda", nullable = false)
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	@OneToMany(mappedBy = "venda")
	public List<ItemVenda> getListaItens() {
		return listaItens;
	}
	public void setListaItens(List<ItemVenda> listaItens) {
		this.listaItens = listaItens;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", vendedor=" + vendedor + ", comprador=" + comprador + ", dataVenda=" + dataVenda
				+ "]";
	}
}
