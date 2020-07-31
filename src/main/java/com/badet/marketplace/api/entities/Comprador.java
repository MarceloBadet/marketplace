package com.badet.marketplace.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comprador")
public class Comprador implements Serializable  {

	private static final long serialVersionUID = 194369582022919826L;
	
	private Long id;
	private String nome;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idComprador")
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
		return "Comprador [id=" + id + ", nome=" + nome + "]";
	}
}
