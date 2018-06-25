package com.thalles.easymanager.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thalles.easymanager.model.Lancamento;
import com.thalles.easymanager.model.Pessoa;
import com.thalles.easymanager.repository.LancamentoRepository;
import com.thalles.easymanager.repository.PessoaRepository;
import com.thalles.easymanager.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());
		
		if(pessoa == null || !pessoa.isAtivo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
		
	}
	
	
	public Lancamento atualizar (Long codigo, Lancamento lancamento) {
		Lancamento saved = buscarLancamentoExistente(codigo);
		
		if(!lancamento.getPessoa().equals(saved.getPessoa())) {
			validarPessoa(lancamento);
		}
		
		BeanUtils.copyProperties(saved, lancamento, "codigo");
		
		return lancamentoRepository.save(saved);
		
	}


	private Lancamento buscarLancamentoExistente(Long codigo) {
		Lancamento existente = lancamentoRepository.findOne(codigo);
		if(existente == null) {
			throw new IllegalArgumentException();
		}
		
		return existente;
		
	}


	private void validarPessoa(Lancamento lancamento) {
		Pessoa pessoa = null;
		if(lancamento.getPessoa().getId() != null) {
			pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());
		}
		
		if(pessoa == null || !(pessoa.isAtivo())) {
			throw new PessoaInexistenteOuInativaException();
		}
		
	}
	
	

}
