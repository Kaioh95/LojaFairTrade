package com.app.LojaFairTrade.service;

import com.app.LojaFairTrade.entity.AppUser;
import com.app.LojaFairTrade.entity.Avaliacao;
import com.app.LojaFairTrade.repository.AppUserRepository;
import com.app.LojaFairTrade.repository.AvaliacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AvaliacaoService {
    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoServiceInterface avaliacaoServiceInterface;

    public String adicionarAvaliacao(Avaliacao avaliacao) {
        return avaliacaoServiceInterface.adicionarAvaliacao(avaliacao);
    }

    public Avaliacao lerAvaliacao(Long id){
        return avaliacaoRepository.findById(id).orElseThrow();
    }

    public Avaliacao removerAvaliacao(Long id){
        boolean avaliacaoExists = avaliacaoRepository.findById(id).isPresent();

        if(avaliacaoExists){
            Avaliacao avaliacaoRemovido = avaliacaoRepository.findById(id).orElseThrow();
            avaliacaoRepository.delete(avaliacaoRemovido);
            return avaliacaoRemovido;
        }
        return null;
    }

    public String atualizarAvaliacao(Avaliacao avaliacao){
        boolean avaliacaoExists = avaliacaoRepository.findById(avaliacao.getId()).isPresent();

        if(avaliacaoExists) {
            avaliacaoRepository.save(avaliacao);
            return "Avaliação atualizada";
        } else {
            return "O avaliação especificada não existe no sistema";
        }
    }

    public List<Avaliacao> listarAvaliacoesPorIDAvaliando(Long id){
        return avaliacaoServiceInterface.listarAvaliacoesPorIDAvaliando(id);
    }

    public List<Avaliacao> listarAvaliacoesPorIDAvaliado(Long id){
        return avaliacaoServiceInterface.listarAvaliacoesPorIDAvaliado(id);
    }

    public List<Avaliacao> listarTodos(){
        return avaliacaoServiceInterface.listarTodos();
    }

    public String mediaPonderada(Long id){
        return avaliacaoServiceInterface.mediaPonderada(id);
    }
}
