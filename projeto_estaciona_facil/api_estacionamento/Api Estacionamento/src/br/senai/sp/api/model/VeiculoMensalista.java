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
public class VeiculoMensalista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_veiculo")
	private Long id;
	private String placa;
	private String modelo;
	
	@Column(name = "id_mensalista")
	private Long idMensalista;
	
	//@ManyToOne
	//@JoinColumn(name = "id_mensalista")
	//private Mensalista mensalista;

	
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

	public Long getIdMensalista() {
		return idMensalista;
	}

	public void setIdMensalista(Long idMensalista) {
		this.idMensalista = idMensalista;
	}

	/*public Mensalista getMensalista() {
		return mensalista;
	}

	public void setMensalista(Mensalista mensalista) {
		this.mensalista = mensalista;
	}*/
	
	
	
}
