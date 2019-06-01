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

import br.senai.sp.api.model.Preco;
import br.senai.sp.api.repository.PrecoRepository;


@RestController
@RequestMapping("/preco")
public class PrecoResource {
		
		@Autowired
		private PrecoRepository precoRepository;
		
		@GetMapping
		public List<Preco> getPreco(){
			return precoRepository.findAll();
		}
		
		@GetMapping("/{id}")
		public Optional<Preco> getPrecos(@PathVariable Long id){
			return precoRepository.findById(id);
		}
		
		@PostMapping
		public ResponseEntity<Preco> gravarPreco(@RequestBody Preco preco, HttpServletResponse response){
			
			preco.setFlag("V");
			
			Preco precoSalvo = precoRepository.save(preco);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(precoSalvo.getId()).toUri();
			
			response.addHeader("Location", uri.toASCIIString());
			
			return ResponseEntity.created(uri).body(precoSalvo);
		}
		
		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deletarPreco(@PathVariable Long id) {
			precoRepository.deleteById(id);
		}
		
		
		@PutMapping("/{id}")
		public ResponseEntity<Preco> desativarPreco(@RequestBody Preco preco, @PathVariable Long id){
			
			preco.setDataFim(Data.getData());
			preco.setFlag("NV");
			Preco precoAtualizado = precoRepository.findById(id).get();
			BeanUtils.copyProperties(preco, precoAtualizado, "id");
			precoRepository.save(preco);
			return ResponseEntity.ok(precoAtualizado);
		}
		
		@GetMapping("/precovigente")
		public Preco getPrecoVigente() {
			return precoRepository.findByDataFimIsNull();
		}
		
		
	}

	

