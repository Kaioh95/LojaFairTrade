package com.app.LojaFairTrade.app1;

import com.app.LojaFairTrade.framework.controller.AvaliacaoController;
import com.app.LojaFairTrade.framework.entity.Avaliacao;
import com.app.LojaFairTrade.framework.service.AvaliacaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fairtradedata/avaliacao")
public class AvaliacaoControllerConcrete extends AvaliacaoController {

    public AvaliacaoControllerConcrete(AvaliacaoService _avaliacaoService){
        super(_avaliacaoService);
    }

    @GetMapping(path="/{id}/avaliacoes-do-user")
    public List<Avaliacao> listarPorIdAvaliando(@PathVariable("id") Long id){
        return avaliacaoService.listarAvaliacoesPorIDAvaliando(id);
    }

    @GetMapping(path="/{id}/avaliacoes-user-recebeu")
    public List<Avaliacao> listarPorIdAvaliado(@PathVariable("id") Long id){
        return avaliacaoService.listarAvaliacoesPorIDAvaliado(id);
    }

    @GetMapping(path = "/{id}/nota")
    public String notaMediaPonderada(@PathVariable("id") Long id){
        return avaliacaoService.mediaPonderada(id);
    }
}
