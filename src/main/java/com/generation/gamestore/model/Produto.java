package com.generation.gamestore.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo jogo nao pode estar vazio.")
	@Size(min = 1, max = 100, message = "O atributo jogo deve conter no mínimo 01 e no máximo 100 caracteres.")
	private String jogo;
	
	@Size(min = 1, max = 100, message = "O atributo desenvolvedora deve conter no mínimo 01 e no máximo 100 caracteres.")
	private String desenvolvedora;
	
	private Long anolancamento;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Usuario usuario;

	@ManyToOne
	@JsonIgnoreProperties("categoria")
	private Categoria categoria;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJogo() {
		return jogo;
	}

	public void setJogo(String jogo) {
		this.jogo = jogo;
	}

	public String getDesenvolvedora() {
		return desenvolvedora;
	}

	public void setDesenvolvedora(String desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}

	public Long getAnolancamento() {
		return anolancamento;
	}

	public void setAnolancamento(Long anolancamento) {
		this.anolancamento = anolancamento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
