package com.eclipze.garyuDojo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "karate")
public class Karate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private double mensalidade;
	
	@NotBlank
	private String faixa;
	
	private String graduacao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(double mensalidade) {
		this.mensalidade = mensalidade;
	}

	public String getFaixa() {
		return faixa;
	}

	public void setFaixa(String faixa) {
		this.faixa = faixa;
	}

	public String getGraduacao() {
		return graduacao;
	}

	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}

	
}
