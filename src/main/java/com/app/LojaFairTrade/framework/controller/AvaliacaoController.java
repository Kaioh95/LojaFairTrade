package com.app.LojaFairTrade.framework.controller;

import com.app.LojaFairTrade.framework.entity.Avaliacao;
import com.app.LojaFairTrade.framework.exceptions.AvaliacaoException;
import com.app.LojaFairTrade.framework.service.AvaliacaoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping(path = "api/v1/fairtradedata/avaliacao")
@AllArgsConstructor
public abstract class AvaliacaoController {
    protected final AvaliacaoService avaliacaoService;

    @PostMapping(path = "/")
    public String adicionaAvaliacao(@RequestBody Avaliacao avaliacao){
        return avaliacaoService.adicionarAvaliacao(avaliacao);
    }

    @GetMapping(path = "/{id}")
    public Avaliacao retornaAvaliacao(@PathVariable("id") Long id){
        return avaliacaoService.lerAvaliacao(id);
    }

    @PutMapping(path = "/")
    public String atualizaAvaliacao(@RequestBody Avaliacao avaliacao){
        try{
            return avaliacaoService.atualizarAvaliacao(avaliacao);
        }catch(AvaliacaoException ex) {
            return ex.getMessage();
        }
    }

    @DeleteMapping(path = "/{id}")
    public Avaliacao removeAvaliacao(@PathVariable("id") Long id){
        try{
            return avaliacaoService.removerAvaliacao(id);
        }catch(AvaliacaoException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @GetMapping(path = "/")
    public List<Avaliacao> listarTodos(){
        return avaliacaoService.listarTodos();
    }

}
