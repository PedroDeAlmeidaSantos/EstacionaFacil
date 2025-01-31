package br.senai.sp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	
	/*RETORNA OS ESTACIONADOS*/
	@Query("select v from Veiculo v where v.horarioSaida is null")
	List<Veiculo> findByHoraSaidaIsNull();
	
	/*RETORNA A PLACA E O VALOR PAGO*/
	@Query("select v from Veiculo v where v.placa = ?1")
	Veiculo findByPlaca(String placa);
	
	@Query("select v from Veiculo v where v.horarioSaida is not null")
	List<Veiculo> findByHoraSaidaIsNotNull();
	
}
