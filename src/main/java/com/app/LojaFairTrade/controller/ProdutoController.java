package com.app.LojaFairTrade.controller;

import com.app.LojaFairTrade.entity.AppUser;
import com.app.LojaFairTrade.entity.Produto;
import com.app.LojaFairTrade.entity.ProdutoCategoria;
import com.app.LojaFairTrade.service.AppUserService;
import com.app.LojaFairTrade.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping(path = "api/v1/fairtradedata/produto")
@AllArgsConstructor
public abstract class ProdutoController {

    protected final ProdutoService produtoService;

    @PostMapping()
    public String criarProduto(@RequestBody Produto produto){
        return produtoService.adicionarProduto(produto);
    }

    @GetMapping(path = "/{id}")
    public Produto retornaProduto(@PathVariable("id") Long id){
        return produtoService.lerProduto(id);
    }

    @PutMapping()
    public String atualizaProduto(@RequestBody Produto produto){
        return produtoService.atualizarProduto(produto);
    }

    @DeleteMapping(path = "/{id}")
    public Produto removeProduto(@PathVariable("id") Long id){
        return produtoService.removerProduto(id);
    }

    @GetMapping(path = "/search")
    public List<Produto> pesquisaPorNome(@RequestParam("nome") String nome){
        return produtoService.pesquisarNome(nome);
    }


}
