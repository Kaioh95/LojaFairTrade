package com.app.LojaFairTrade.framework.service;

import com.app.LojaFairTrade.framework.entity.Produto;
import com.app.LojaFairTrade.framework.entity.ProdutoCategoria;

import java.util.List;

public interface ProdutoStrategy {

    public String adicionarProduto(Produto produto);

    public List<Produto> pesquisarNome(String nome);

    public List<Produto> buscarCategoria(ProdutoCategoria categoria);

    public List<Produto> produtosIntervaloPreco(float p1, float p2);

    public List<Produto> todosProdutos();

    public String calcularFrete(Long codigoProduto, Long cepDestino);
}
