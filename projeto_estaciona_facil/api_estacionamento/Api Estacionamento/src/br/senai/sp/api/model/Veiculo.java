package br.senai.sp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_movimentacao")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String placa;
	private String modelo;
	private String horarioEntrada;
	private String horarioSaida;
	private Integer tempo;
	private Double valorPago;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getHorarioEntrada() {
		return horarioEntrada;
		
	}

	public void setHorarioEntrada(String horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public String getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(String horarioSaida) {
		this.horarioSaida = horarioSaida;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public Double getValor() {
		return valorPago;
	}

	public void setValor(Double valor) {
		this.valorPago = valor;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", placa=" + placa + ", modelo=" + modelo + ", horarioEntrada=" + horarioEntrada
				+ ", horarioSaida=" + horarioSaida + ", tempo=" + tempo + ", valor=" + valorPago + "]";
	}

}
