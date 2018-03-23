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
import com.thalles.easymanager.model.Lancamento;
import com.thalles.easymanager.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository repositorio;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Lancamento> listar(){
		return repositorio.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloId(@PathVariable Long codigo){
		Lancamento lancamento = repositorio.findOne(codigo);
		return lancamento == null? ResponseEntity.notFound().build() : ResponseEntity.ok(lancamento);
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> adicionar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse resposta){
		
		Lancamento instanciaSalva = repositorio.save(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, resposta, instanciaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(instanciaSalva);
		
	}
	
}
