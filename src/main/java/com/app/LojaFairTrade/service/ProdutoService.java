package com.app.LojaFairTrade.service;

import com.app.LojaFairTrade.entity.Produto;
import com.app.LojaFairTrade.entity.ProdutoCategoria;
import com.app.LojaFairTrade.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public String adicionarProduto(Produto produto){
        boolean produtoExists = produtoRepository.findById(produto.getId()).isPresent();

        if(!produtoExists){
            try{
                if(produto.getDesconto()<0 || produto.getDesconto() > 1) {
                    return "Desconto fora do intervalo aceito";
                }
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

    public List<Produto> pesquisarNome(String nome){
        return produtoRepository.findByNome(nome);
    }

    public List<Produto> compararPrecos(){
        return produtoRepository.ordernarPorPreco();
    }

    public List<Produto> todosProdutos(){
        return produtoRepository.findAll();
    }

    public List<Produto> listarCategoria(ProdutoCategoria categoria){
        return produtoRepository.findByCategoria(categoria);
    }
}
