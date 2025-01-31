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
import br.senai.sp.api.model.Veiculo;
import br.senai.sp.api.repository.PrecoRepository;
import br.senai.sp.api.repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculo")
public class VeiculoResource {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private PrecoRepository precoRepository;
	
	@GetMapping
	public List<Veiculo> getVeiculos(){
		return veiculoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Veiculo> getVeiculos(@PathVariable Long id){
		return veiculoRepository.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarVeiculo(@PathVariable Long id) {
		veiculoRepository.deleteById(id);
	}
	
	
	@PutMapping("/saida/{id}")
	public ResponseEntity<Veiculo> atualizarVeiculoHora(@RequestBody Veiculo veiculo, @PathVariable Long id){
		
		
		
		Pagamento pagamento = new Pagamento();
		veiculo.setValor(Pagamento.getPagamento(veiculo, this.precoRepository.findByDataFimIsNull()));
		veiculo.setHorarioSaida(Data.getData());
		Veiculo veiculoAtualizado = veiculoRepository.findById(id).get();
		BeanUtils.copyProperties(veiculo, veiculoAtualizado, "id");
		
		veiculoRepository.save(veiculo);
		return ResponseEntity.ok(veiculoAtualizado);
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> atualizarVeiculo(@RequestBody Veiculo veiculo, @PathVariable Long id){
		
		Veiculo veiculoAtualizado = veiculoRepository.findById(id).get();
		BeanUtils.copyProperties(veiculo, veiculoAtualizado, "id");
		veiculoRepository.save(veiculo);
		return ResponseEntity.ok(veiculoAtualizado);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Veiculo> cadastrarVeiculo(@RequestBody Veiculo veiculo, HttpServletResponse response){
		
		veiculo.setHorarioEntrada(Data.getData());
		
		Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(veiculoSalvo.getId()).toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(veiculoSalvo);
	}
	
	@GetMapping("/estacionados")
	public List<Veiculo> getEstacionados(){
		return veiculoRepository.findByHoraSaidaIsNull();
	}
	
	@GetMapping("/saida")
	public List<Veiculo> getVeiculosSaida(){
		return veiculoRepository.findByHoraSaidaIsNotNull();
	}
	
	
	
	@GetMapping("/placa/{placa}")
	public Veiculo getVeiculoPlaca(@PathVariable String placa){
		Veiculo veiculo = new Veiculo();
		//Pagamento pagamento = new Pagamento();
		//pagamento.getPagamento(veiculo);
		return veiculoRepository.findByPlaca(placa);
		
		
	}
	
	
	
	
	
	
}
