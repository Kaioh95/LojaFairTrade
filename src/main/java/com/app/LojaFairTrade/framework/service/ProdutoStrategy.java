package com.app.LojaFairTrade.framework.service;

import com.app.LojaFairTrade.framework.entity.Produto;
import com.app.LojaFairTrade.framework.entity.ProdutoCategoria;

import java.util.List;

public interface ProdutoStrategy {

    public String adicionarProduto(Produto produto);

    public List<Produto> pesquisarNome(String nome);

    public List<Produto> compararPrecos();

    public List<Produto> todosProdutos();

    public List<Produto> listarCategoria(ProdutoCategoria categoria);

    public String calcularFrete(Long codigoProduto, Long cepDestino);
}
