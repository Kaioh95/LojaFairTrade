package com.app.LojaFairTrade.service;

import com.app.LojaFairTrade.entity.Produto;
import com.app.LojaFairTrade.entity.ProdutoCategoria;
import com.app.LojaFairTrade.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoServiceInterface produtoServiceInterface;

    public String adicionarProduto(Produto produto){
        return produtoServiceInterface.adicionarProduto(produto);
    }

    public Produto lerProduto(Long id){
        return produtoRepository.findById(id).orElseThrow();
    }

    public Produto removerProduto(Long id){
        boolean produtoExists = produtoRepository.findById(id).isPresent();

        if(produtoExists){
            Produto produtoRemovido = produtoRepository.findById(id).orElseThrow();
            produtoRepository.delete(produtoRemovido);
            return produtoRemovido;
        }
        return null;
    }

    public String atualizarProduto(Produto produto){
        boolean produtoExists = produtoRepository.findById(produto.getId()).isPresent();

        if(produtoExists) {
            if(produto.getDesconto()<0 || produto.getDesconto() > 1) {
                return "Desconto fora do intervalo aceito [0, 1]";
            }
            produtoRepository.save(produto);
            return "Produto atualizado";
        } else {
            return "O produto especificado não existe no sistema";
        }
    }

    public List<Produto> pesquisarNome(String nome){
        return produtoServiceInterface.pesquisarNome(nome);
    }

    public List<Produto> compararPrecos(){
        return produtoServiceInterface.compararPrecos();
    }

    public List<Produto> todosProdutos(){
        return produtoServiceInterface.todosProdutos();
    }

    public List<Produto> listarCategoria(ProdutoCategoria categoria){
        return produtoServiceInterface.listarCategoria(categoria);
    }

    public String calcularFrete(Long codigoProduto, Long cepDestino){
        return produtoServiceInterface.calcularFrete(codigoProduto, cepDestino);
    }

    public List<Produto> listarProdutosPorUser(Long id){
        return produtoServiceInterface.listarProdutosPorUser(id);
    }
}
