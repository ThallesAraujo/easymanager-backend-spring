package com.thalles.easymanager.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalles.easymanager.event.RecursoCriadoEvent;
import com.thalles.easymanager.model.Pessoa;
import com.thalles.easymanager.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository repositorio; 
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Pessoa> listar(){
		return repositorio.findAll();
	}

	
	@PostMapping
	public ResponseEntity<Pessoa> adicionar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse resposta){
		Pessoa instanciaSalva = repositorio.save(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, resposta, instanciaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(instanciaSalva);
	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPeloId(@PathVariable Long id){
		Pessoa ps = repositorio.findOne(id);
		return ps==null? ResponseEntity.notFound().build() : ResponseEntity.ok(ps);
	}
}
