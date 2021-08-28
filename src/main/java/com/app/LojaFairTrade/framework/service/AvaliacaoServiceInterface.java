package com.app.LojaFairTrade.framework.service;

import com.app.LojaFairTrade.framework.entity.Avaliacao;

import java.util.List;

public interface AvaliacaoServiceInterface {

    public String adicionarAvaliacao(Avaliacao avaliacao);

    public List<Avaliacao> listarAvaliacoesPorIDAvaliando(Long id);

    public List<Avaliacao> listarAvaliacoesPorIDAvaliado(Long id);

    public List<Avaliacao> listarTodos();

    public String mediaPonderada(Long id);
}
