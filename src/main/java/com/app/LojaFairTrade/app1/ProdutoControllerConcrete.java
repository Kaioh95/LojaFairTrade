package com.app.LojaFairTrade.app1;

import com.app.LojaFairTrade.framework.controller.ProdutoController;
import com.app.LojaFairTrade.framework.entity.Produto;
import com.app.LojaFairTrade.framework.entity.ProdutoCategoria;
import com.app.LojaFairTrade.framework.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Factory Method
@RestController
@RequestMapping(path = "api/v1/fairtradedata/produto")
public class ProdutoControllerConcrete extends ProdutoController {

    public ProdutoControllerConcrete(ProdutoService _produtoService){
        super(_produtoService);
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

    @GetMapping(path = "/frete/{id}")
    public String calcularFrete(@PathVariable("id") Long id
            , @RequestParam("cepDestino") Long cepDestino){
        return produtoService.calcularFrete(id, cepDestino);
    }

    @GetMapping(path = "/produtos-user/{id}")
    public List<Produto> listarProdutosPorUser(@PathVariable("id") Long id){
        return produtoService.listarProdutosPorUser(id);
    }
}
