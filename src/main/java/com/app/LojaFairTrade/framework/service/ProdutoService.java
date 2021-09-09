package com.app.LojaFairTrade.framework.service;

import com.app.LojaFairTrade.framework.entity.Produto;
import com.app.LojaFairTrade.framework.entity.ProdutoCategoria;
import com.app.LojaFairTrade.framework.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoStrategy produtoStrategy;

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

    public String adicionarProduto(Produto produto){
        return produtoStrategy.adicionarProduto(produto);
    }

    public List<Produto> pesquisarNome(String nome){
        return produtoStrategy.pesquisarNome(nome);
    }

    public List<Produto> buscarIntervalopreco(float p1, float p2){
        return produtoStrategy.produtosIntervaloPreco(p1, p2);
    }

    public List<Produto> todosProdutos(){
        return produtoStrategy.todosProdutos();
    }

    public List<Produto> buscarPorCategoria(ProdutoCategoria categoria){
        return produtoStrategy.buscarCategoria(categoria);
    }

    public String calcularFrete(Long codigoProduto, Long cepDestino){
        return produtoStrategy.calcularFrete(codigoProduto, cepDestino);
    }
}
