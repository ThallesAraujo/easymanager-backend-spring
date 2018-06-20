package com.thalles.easymanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thalles.easymanager.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	public Page<Pessoa> findByNomeContaining(String nome, Pageable page);
	
}
