package com.app.LojaFairTrade.service;

import com.app.LojaFairTrade.entity.AppUser;
import com.app.LojaFairTrade.entity.AppUserRole;
import com.app.LojaFairTrade.entity.Produto;
import com.app.LojaFairTrade.entity.ProdutoCategoria;
import com.app.LojaFairTrade.repository.AppUserRepository;
import com.app.LojaFairTrade.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final AppUserRepository appUserRepository;
    private final ProdutoServiceCompositor produtoServiceCompositor;

    public String adicionarProduto(Produto produto){
        return produtoServiceCompositor.adicionarProduto(produto);
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
        return produtoServiceCompositor.pesquisarNome(nome);
    }

    public List<Produto> compararPrecos(){
        return produtoServiceCompositor.compararPrecos();
    }

    public List<Produto> todosProdutos(){
        return produtoServiceCompositor.todosProdutos();
    }

    public List<Produto> listarCategoria(ProdutoCategoria categoria){
        return produtoServiceCompositor.listarCategoria(categoria);
    }

    public String calcularFrete(Long codigoProduto, Long cepDestino){
        return produtoServiceCompositor.calcularFrete(codigoProduto, cepDestino);
    }
}
