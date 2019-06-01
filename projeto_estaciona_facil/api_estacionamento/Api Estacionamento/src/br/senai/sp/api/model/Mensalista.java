package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_mensalista")
public class Mensalista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMensalista;
	private String nome;
	private String cpf;
	private String celular;
	private String qtd_vagas;

	public Long getId() {
		return idMensalista;
	}

	public void setId(Long id) {
		this.idMensalista = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getQtd_vagas() {
		return qtd_vagas;
	}

	public void setQtd_vagas(String qtd_vagas) {
		this.qtd_vagas = qtd_vagas;
	}

	@Override
	public String toString() {
		return "Mensalista [id=" + idMensalista + ", nome=" + nome + ", cpf=" + cpf + ", celular=" + celular + ", qtd_vagas="
				+ qtd_vagas + "]";
	}

}
