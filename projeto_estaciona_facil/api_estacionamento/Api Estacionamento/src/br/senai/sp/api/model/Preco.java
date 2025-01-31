package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_preco")
public class Preco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valorPrimeiraHora;
	private Double valorDemaisHoras;
	private String dataFim;
	private Integer tolerancia;
	private String flag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValorPrimeiraHora() {
		return valorPrimeiraHora;
	}

	public void setValorPrimeiraHora(Double valorPrimeiraHora) {
		this.valorPrimeiraHora = valorPrimeiraHora;
	}

	public Double getValorDemaisHoras() {
		return valorDemaisHoras;
	}

	public void setValorDemaisHoras(Double valorDemaisHoras) {
		this.valorDemaisHoras = valorDemaisHoras;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(Integer tolerancia) {
		this.tolerancia = tolerancia;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Preco [id=" + id + ", valorPrimeiraHora=" + valorPrimeiraHora + ", valorDemaisHoras=" + valorDemaisHoras
				+ ", dataFim=" + dataFim + ", tolerancia=" + tolerancia + ", flag=" + flag + "]";
	}

	

}
