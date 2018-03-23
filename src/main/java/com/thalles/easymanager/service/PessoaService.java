package com.thalles.easymanager.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.thalles.easymanager.model.Pessoa;
import com.thalles.easymanager.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repositorio;

	public Pessoa atualizar(Long id, Pessoa pessoa) {
		Pessoa instanciaSalva = procurarPeloId(id);
		BeanUtils.copyProperties(pessoa, instanciaSalva, "id");
		return repositorio.save(instanciaSalva);

	}

	public  Pessoa procurarPeloId(Long id) {
		Pessoa instanciaSalva = repositorio.findOne(id);
		if(instanciaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}else {
			return instanciaSalva;
		}
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		
		Pessoa instanciaSalva = procurarPeloId(id);
		instanciaSalva.setAtivo(ativo);
		repositorio.save(instanciaSalva);
		
	}

}
