package com.thalles.easymanager.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalles.easymanager.event.RecursoCriadoEvent;
import com.thalles.easymanager.model.Categoria;
import com.thalles.easymanager.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository repositorio;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
//	Para retornar 404 ou qualquer outra resposta HTTP mais adequada, utilizar ResponseEntity
//	Ex.: ResponseEntity.ok(<<objeto>>)
//		ResponseEntity.notFound().build()
//		ResponseEntity.noContent().build
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public List<Categoria> listar(){
		return repositorio.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
//	Para retornar um verbo HTTP mais adequado para opção de criar algum recurso no banco de dados,
//	utiliza-se a anotação ResponseStatus(HttpStatus.<<STATUS>>) caso não se utilize o ResponseEntity
//	Notação ResquestBody: indicar que a resposta para a requisição deve ser um objeto
//  Notação Valid: validação do objeto
	public ResponseEntity<Categoria> adicionar(@Valid @RequestBody Categoria categoria, HttpServletResponse resposta) {
		Categoria categoriaSalva =  repositorio.save(categoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, resposta, categoriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	//PathVariable: indica a variável indicada no GetMapping ({codigo})
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
		//repositorio.getOne(<<id>>): utilizado somente quando assume-se que o objeto já existe
		//repositorio.findOne(<<id>>): 
		Categoria cat = repositorio.findOne(codigo);
		return cat==null? ResponseEntity.notFound().build() : ResponseEntity.ok(cat);
	}

}
