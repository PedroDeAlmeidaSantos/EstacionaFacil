package br.senai.sp.api.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Preco;

public interface PrecoRepository extends JpaRepository<Preco, Long> {
	
	@Query("select v from Preco v where v.dataFim is null")
	Preco findByDataFimIsNull();
}
