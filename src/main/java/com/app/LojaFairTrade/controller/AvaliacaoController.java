package com.app.LojaFairTrade.controller;

import com.app.LojaFairTrade.entity.Avaliacao;
import com.app.LojaFairTrade.service.AvaliacaoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fairtradedata/avaliacao")
@AllArgsConstructor
public class AvaliacaoController {
    private final AvaliacaoService avaliacaoService;

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
        return avaliacaoService.atualizarAvaliacao(avaliacao);
    }

    @DeleteMapping(path = "/{id}")
    public Avaliacao removeAvaliacao(@PathVariable("id") Long id){
        return avaliacaoService.removerAvaliacao(id);
    }

    @GetMapping(path = "/")
    public List<Avaliacao> listarTodos(){
        return avaliacaoService.listarTodos();
    }
}
