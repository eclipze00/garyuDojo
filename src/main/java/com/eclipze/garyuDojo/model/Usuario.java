package com.eclipze.garyuDojo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name= "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@NotBlank
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Schema(example = "email@email.com")
	@NotBlank(message = "O Atributo e-mail é obrigatório!")
	@Email(message = "Deve ser um e-mail válido!")
	private String email;
	
	@NotBlank
	@Size (min = 5, max = 100)
	private String senha;
	
	private String foto;
	
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Karate> karate;
	
	
	

	public Usuario(long id, String nome, Date data, String email, String senha, String foto) {
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
	}
	

	public Usuario() {
	}

	


	public List<Karate> getKarate() {
		return karate;
	}


	public void setKarate(List<Karate> karate) {
		this.karate = karate;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	

}
