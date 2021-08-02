package com.app.LojaFairTrade.controller;

import com.app.LojaFairTrade.entity.Produto;
import com.app.LojaFairTrade.entity.ProdutoCategoria;
import com.app.LojaFairTrade.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fairtradedata/produto")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

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

    @GetMapping(path = "/")
    public List<Produto> listarTodos(){
        return produtoService.listarTodos();
    }

    @GetMapping(path = "/search")
    public List<Produto> pesquisaPorNome(@RequestParam("nome") String nome){
        return produtoService.pesquisarNome(nome);
    }

    @GetMapping(path = "/menores-precos")
    public List<Produto> comparadorPrecos(){
        return produtoService.compararPrecos();
    }

    @GetMapping(path = "/todos-produtos")
    public List<Produto> mostrarTodosProdutos(){
        return produtoService.todosProdutos();
    }

    @GetMapping(path = "/categorias/")
    public List<Produto> listarPorCategoria(@RequestParam("categoria") ProdutoCategoria categoria){
        return produtoService.listarCategoria(categoria);
    }

}
