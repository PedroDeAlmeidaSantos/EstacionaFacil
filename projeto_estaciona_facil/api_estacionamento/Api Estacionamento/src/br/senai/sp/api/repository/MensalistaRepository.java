package br.senai.sp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.model.Preco;
import br.senai.sp.api.model.Veiculo;

public interface MensalistaRepository extends JpaRepository<Mensalista, Long> {

	
	@Query("select m from Mensalista m where m.cpf = ?1")
	Mensalista findByCpf(String cpf);
	
	
	
}
