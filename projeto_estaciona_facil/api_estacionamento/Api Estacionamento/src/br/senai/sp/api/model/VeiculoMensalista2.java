package br.senai.sp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_veiculo")
public class VeiculoMensalista2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_veiculo")
	private Long codVeiculo;
	
	@Column(name = "placa")
	private String placa;
	private String modelo;
	
	@ManyToOne
	@JoinColumn(name = "cod_fabricante")
	private Fabricante fabricante;

	public Long getCodVeiculo() {
		return codVeiculo;
	}

	public void setCodVeiculo(Long codVeiculo) {
		this.codVeiculo = codVeiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
