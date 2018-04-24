package com.thalles.easymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thalles.easymanager.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	
	
}
