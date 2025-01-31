package br.senai.sp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.model.Veiculo;
import br.senai.sp.api.model.VeiculoMensalista;

public interface VeiculoMensalistaRepository extends JpaRepository<VeiculoMensalista, Long> {

	@Query("SELECT v from VeiculoMensalista v where v.idMensalista = ?1")
	List<VeiculoMensalista> findByVeiculoId(Long id);

	
	@Transactional
	@Modifying
	@Query("DELETE FROM VeiculoMensalista vm WHERE vm.idMensalista = ?1")
	void deleteAllById(Long id);
	
	
	//@Query("select m from Mensalista m where m.cpf = ?1")
	//Mensalista findByCpf(String cpf);
	
}