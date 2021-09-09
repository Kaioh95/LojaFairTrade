package com.app.LojaFairTrade.framework.service;

import com.app.LojaFairTrade.framework.entity.Avaliacao;

import java.util.List;

public interface AvaliacaoStrategy {

    public String adicionarAvaliacao(Avaliacao avaliacao);

    public List<Avaliacao> listarTodos();

    public List<Avaliacao> listarAvaliacoesProduto(Long id);

    public String mediaAritmetica(Long id);
}
