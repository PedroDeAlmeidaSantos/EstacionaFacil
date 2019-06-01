package br.senai.sp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_fabricante")
public class Fabricante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_fabricante")
	private Long codFabricante;
	
	@Column(name = "nome")
	private String nome;

	public Long getCodFabricante() {
		return codFabricante;
	}

	public void setCodFabricante(Long codFabricante) {
		this.codFabricante = codFabricante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
