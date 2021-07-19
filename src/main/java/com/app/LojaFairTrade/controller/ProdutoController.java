package com.app.LojaFairTrade.controller;

import com.app.LojaFairTrade.entity.Produto;
import com.app.LojaFairTrade.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/fairtradedata/produto")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping()
    public String criarProduto(@RequestBody Produto produto){
        return produtoService.adicionarProduto(produto);
    }

    @GetMapping(path="/{id}")
    public Produto retornaProduto(@RequestParam Long id){
        return produtoService.lerProduto(id);
    }

    @PutMapping()
    public String atualizaProduto(@RequestBody Produto produto){
        return produtoService.atualizarProduto(produto);
    }

    @DeleteMapping(path="/{id}")
    public Produto removeProduto(@RequestParam Long id){
        return produtoService.removerProduto(id);
    }
}
