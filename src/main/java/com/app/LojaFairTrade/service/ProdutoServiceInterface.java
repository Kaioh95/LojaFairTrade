package com.app.LojaFairTrade.service;

import com.app.LojaFairTrade.entity.Produto;
import com.app.LojaFairTrade.entity.ProdutoCategoria;

import java.util.List;

public interface ProdutoServiceInterface {

    public List<Produto> pesquisarNome(String nome);

    public List<Produto> compararPrecos();

    public List<Produto> todosProdutos();

    public List<Produto> listarCategoria(ProdutoCategoria categoria);

    public String calcularFrete(Long codigoProduto, Long cepDestino);

}
