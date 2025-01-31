package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import br.senai.sp.api.model.Veiculo;
import br.senai.sp.api.repository.MensalistaRepository;
import br.senai.sp.api.repository.PrecoRepository;
import br.senai.sp.api.repository.VeiculoMensalistaRepository;

@RestController
@RequestMapping("/mensalista")
public class MensalistaResource {
	
	@Autowired
	private MensalistaRepository mensalistaRepository;
	
	@Autowired
	private VeiculoMensalistaRepository veiculoMensalistaRepository;
	
	@GetMapping
	public List<Mensalista> getMensalistas(){
		return mensalistaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Mensalista> getMensalista(@PathVariable Long id){
		return mensalistaRepository.findById(id);
	}
	
	@GetMapping("/cpf/{cpf}")
	public Mensalista getCpf(@PathVariable String cpf) {
		return mensalistaRepository.findByCpf(cpf);
	}
	
	/*@GetMapping("/precovigente")
		public Preco getPrecoVigente() {
			return precoRepository.findByDataFimIsNull();
		}*/
	
	
	@PostMapping
	public ResponseEntity<Mensalista> gravarMensalista(@RequestBody Mensalista mensalista, HttpServletResponse response){
		Mensalista mensalistaSalvo = mensalistaRepository.save(mensalista);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(mensalistaSalvo.getId()).toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(mensalistaSalvo);
	}
	
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPreco(@PathVariable Long id) {
		
		veiculoMensalistaRepository.deleteAllById(id);
		mensalistaRepository.deleteById(id);
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Mensalista> atualizar(@RequestBody Mensalista mensalista, @PathVariable Long id){
		
		Mensalista mensalistaAtualizado = mensalistaRepository.findById(id).get();
		BeanUtils.copyProperties(mensalista, mensalistaAtualizado, "id");
		mensalistaRepository.save(mensalista);
		return ResponseEntity.ok(mensalistaAtualizado);
	}
	
	
	
}
