package com.app.LojaFairTrade.service;

import com.app.LojaFairTrade.entity.Avaliacao;
import com.app.LojaFairTrade.repository.AvaliacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvaliacaoService {
    private final AvaliacaoRepository avaliacaoRepository;

    public String adicionarAvaliacao(Avaliacao avaliacao){
        boolean avaliacaoExists = avaliacaoRepository.findById(avaliacao.getId()).isPresent();

        if(!avaliacaoExists){
            try{
                if(avaliacao.getTextoAvaliacao().length() > 255) {
                    return "Texto de avaliação maior que 255 caracteres.";
                }else if(avaliacao.getNota() > 5 || avaliacao.getNota() < 0){
                    return "A nota deve ser entre 0 e 5.";
                }
                avaliacaoRepository.save(avaliacao);
                return "Avaliação cadastrada com sucesso";
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return "Erro ao cadastrar avaliação";
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

    public List<Avaliacao> listarTodosPorId(Long id){
        return avaliacaoRepository.findAllById(id);
    }

    public List<Avaliacao> listarTodos(){
        return avaliacaoRepository.findAll();
    }
}
