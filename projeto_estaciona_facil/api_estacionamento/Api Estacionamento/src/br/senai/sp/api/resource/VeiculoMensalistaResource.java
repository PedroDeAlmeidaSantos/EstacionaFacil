package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.model.Preco;
import br.senai.sp.api.model.VeiculoMensalista;
import br.senai.sp.api.repository.MensalistaRepository;
import br.senai.sp.api.repository.VeiculoMensalistaRepository;

@RestController
@RequestMapping("/veiculomensalista")
public class VeiculoMensalistaResource {
	
	@Autowired
	private VeiculoMensalistaRepository veiculoMensalistaRepository;
	
	@Autowired
	private MensalistaRepository mensalistaRepository;
	
	@GetMapping
	public List<VeiculoMensalista> getVeiculoMensalistas(){
		return veiculoMensalistaRepository.findAll();
	}
	
	@GetMapping("/mensalista/{id}")
	public List<VeiculoMensalista> getVeiculoMensalista(@PathVariable Long id){
		
		return veiculoMensalistaRepository.findByVeiculoId(id);
	}
	
	
	
	/*	@PostMapping
	public ResponseEntity<Mensalista> gravarMensalista(@RequestBody Mensalista mensalista, HttpServletResponse response){
		Mensalista mensalistaSalvo = mensalistaRepository.save(mensalista);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(mensalistaSalvo.getId()).toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(mensalistaSalvo);
	}*/
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<VeiculoMensalista> cadastrarVeiculo(@RequestBody VeiculoMensalista veiculoMensalista, HttpServletResponse response) {
		
		VeiculoMensalista veiculoMensalistaSalvo = veiculoMensalistaRepository.save(veiculoMensalista);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(veiculoMensalistaSalvo.getId()).toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(veiculoMensalistaSalvo);
		
		
		//veiculoMensalistaRepository.save(veiculoMensalista);
		
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarVeiculo(@PathVariable Long id) {
		veiculoMensalistaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VeiculoMensalista> atualizarVeiculoMensalista(@RequestBody VeiculoMensalista veiculoMensalista, @PathVariable Long id){
	
		VeiculoMensalista veiculoMensalistaAtualizado = veiculoMensalistaRepository.findById(id).get();
		BeanUtils.copyProperties(veiculoMensalista, veiculoMensalistaAtualizado, "id");
		veiculoMensalistaRepository.save(veiculoMensalista);
		return ResponseEntity.ok(veiculoMensalistaAtualizado);
	}
	
	
	
	
	
	
	
	
	
}
