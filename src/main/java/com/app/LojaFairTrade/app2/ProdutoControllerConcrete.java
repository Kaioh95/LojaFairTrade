package com.app.LojaFairTrade.app2;

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

    @GetMapping(path = "/todos-produtos")
    public List<Produto> mostrarTodosProdutos(){
        return produtoService.todosProdutos();
    }

    @GetMapping(path = "/nome")
    public List<Produto> listarPorNome(@RequestParam("nome") String nome){
        return produtoService.pesquisarNome(nome);
    }

    @GetMapping(path = "/intervalo-preco")
    public List<Produto> listarPorNome(@RequestParam("preco1") float p1, @RequestParam("preco2") float p2){
        return produtoService.buscarIntervalopreco(p1, p2);
    }

    @GetMapping(path = "/categorias/")
    public List<Produto> listarPorCategoria(@RequestParam("categoria") ProdutoCategoria categoria){
        return produtoService.buscarPorCategoria(categoria);
    }

    @GetMapping(path = "/frete/{id}")
    public String calcularFrete(@PathVariable("id") Long id
            , @RequestParam("cepDestino") Long cepDestino){
        return produtoService.calcularFrete(id, cepDestino);
    }
}
