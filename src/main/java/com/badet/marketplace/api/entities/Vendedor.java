package com.badet.marketplace.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendedor")
public class Vendedor implements Serializable  {

	private static final long serialVersionUID = -998888962981921668L;

	private Long id;
	private String nome;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idVendedor")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Vendedor [id=" + id + ", nome=" + nome + "]";
	}
}
