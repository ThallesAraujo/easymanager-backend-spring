package com.thalles.easymanager.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
