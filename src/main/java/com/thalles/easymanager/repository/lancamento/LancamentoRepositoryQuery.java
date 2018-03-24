package com.thalles.easymanager.repository.lancamento;

import java.util.List;

import com.thalles.easymanager.model.Lancamento;
import com.thalles.easymanager.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter filter);

}
