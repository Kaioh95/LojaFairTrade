package com.app.LojaFairTrade.service;

import com.app.LojaFairTrade.entity.Produto;
import com.app.LojaFairTrade.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public String adicionarProduto(Produto produto){
        boolean produtoExists = produtoRepository.findById(produto.getId()).isPresent();

        if(!produtoExists){
            try{
                produtoRepository.save(produto);
                return "Produto cadastrado com sucesso";
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return "Erro ao cadastrar produto";
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
            produtoRepository.save(produto);
            return "Produto atualizado";
        } else {
            return "O produto especificado não existe no sistema";
        }
    }
}
