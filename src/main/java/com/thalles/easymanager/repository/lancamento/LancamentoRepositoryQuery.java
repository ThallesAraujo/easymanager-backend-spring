package com.thalles.easymanager.repository.lancamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thalles.easymanager.model.Lancamento;
import com.thalles.easymanager.repository.filter.LancamentoFilter;
import com.thalles.easymanager.repository.projection.ResumoLancamentoProjection;

public interface LancamentoRepositoryQuery {
	
	public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable);
	public Page<ResumoLancamentoProjection> resumir(LancamentoFilter filter, Pageable pageable);

}
