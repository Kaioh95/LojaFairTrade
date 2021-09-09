package com.app.LojaFairTrade.app2;

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

    @GetMapping(path = "/{id}/nota")
    public String notaMediaAritmetica(@PathVariable("id") Long id){
        return avaliacaoService.mediaAritmetica(id);
    }

    @GetMapping(path = "/todas/{id}")
    public List<Avaliacao> listarPorProduto(@PathVariable("id") Long id){
        return avaliacaoService.listarPorProduto(id);
    }
}
