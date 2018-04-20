package com.thalles.easymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thalles.easymanager.model.Lancamento;
import com.thalles.easymanager.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends  JpaRepository<Lancamento,Long>, LancamentoRepositoryQuery{

}
