package com.thalles.easymanager.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thalles.easymanager.model.Categoria;
import com.thalles.easymanager.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository repositorio;
	
	
//	Para retornar 404 ou qualquer outra resposta HTTP mais adequada, utilizar ResponseEntity
//	Ex.: ResponseEntity.ok(<<objeto>>)
//		ResponseEntity.notFound().build()
//		ResponseEntity.noContent().build
	
	@GetMapping
	public List<Categoria> listar(){
		return repositorio.findAll();
	}
	
	@PostMapping
//	Para retornar um verbo HTTP mais adequado para opção de criar algum recurso no banco de dados,
//	utiliza-se a anotação ResponseStatus(HttpStatus.<<STATUS>>) caso não se utilize o ResponseEntity
//	Notação ResquestBody: indicar que a resposta para a requisição deve ser um objeto
	public ResponseEntity<Categoria> adicionar(@RequestBody Categoria categoria, HttpServletResponse resposta) {
		Categoria categoriaSalva =  repositorio.save(categoria);
		//informar no header da requisição a URI de localização (usada para recuperar) do recurso
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();
		resposta.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	//PathVariable: indica a variável indicada no GetMapping ({codigo})
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
		//repositorio.getOne(<<id>>): utilizado somente quando assume-se que o objeto já existe
		//repositorio.findOne(<<id>>): 
		Categoria cat = repositorio.findOne(codigo);
		return cat==null? ResponseEntity.notFound().build() : ResponseEntity.ok(cat);
	}

}
