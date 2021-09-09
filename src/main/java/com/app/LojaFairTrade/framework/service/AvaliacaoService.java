package com.app.LojaFairTrade.framework.service;

import com.app.LojaFairTrade.framework.entity.Avaliacao;
import com.app.LojaFairTrade.framework.exceptions.AvaliacaoException;
import com.app.LojaFairTrade.framework.repository.AvaliacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvaliacaoService {
    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoStrategy avaliacaoStrategy;

    public String adicionarAvaliacao(Avaliacao avaliacao) {
        return avaliacaoStrategy.adicionarAvaliacao(avaliacao);
    }

    public Avaliacao lerAvaliacao(Long id){
        return avaliacaoRepository.findById(id).orElseThrow();
    }

    public Avaliacao removerAvaliacao(Long id) throws AvaliacaoException{
        boolean avaliacaoExists = avaliacaoRepository.findById(id).isPresent();

        if(avaliacaoExists){
            Avaliacao avaliacaoRemovido = avaliacaoRepository.findById(id).orElseThrow();
            avaliacaoRepository.delete(avaliacaoRemovido);
            return avaliacaoRemovido;
        } else {
            throw new AvaliacaoException("essa avaliação não existe no banco.");
        }
    }

    public String atualizarAvaliacao(Avaliacao avaliacao) throws AvaliacaoException {
        boolean avaliacaoExists = avaliacaoRepository.findById(avaliacao.getId()).isPresent();
        if (avaliacaoExists) {
            avaliacaoRepository.save(avaliacao);
            return "Avaliação atualizada";
        } else {
            throw new AvaliacaoException("não foi possível salvar no banco.");
        }
    }

    public List<Avaliacao> listarTodos(){
        return avaliacaoStrategy.listarTodos();
    }

    public List<Avaliacao> listarPorProduto(Long id){
        return avaliacaoStrategy.listarAvaliacoesProduto(id);
    }

    public String mediaAritmetica(Long id){
        return avaliacaoStrategy.mediaAritmetica(id);
    }
}
