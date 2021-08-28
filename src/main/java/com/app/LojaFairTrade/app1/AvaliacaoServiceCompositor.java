package com.app.LojaFairTrade.app1;

import com.app.LojaFairTrade.framework.entity.AppUser;
import com.app.LojaFairTrade.framework.entity.AppUserRole;
import com.app.LojaFairTrade.framework.entity.Avaliacao;
import com.app.LojaFairTrade.framework.repository.AppUserRepository;
import com.app.LojaFairTrade.framework.repository.AvaliacaoRepository;
import com.app.LojaFairTrade.framework.service.AvaliacaoServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AvaliacaoServiceCompositor implements AvaliacaoServiceInterface {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AppUserRepository appUserRepository;

    public String adicionarAvaliacao(Avaliacao avaliacao) {
        AppUser userAvaliandoExists;
        AppUser userAvaliadoExists;
        try {
            userAvaliandoExists = appUserRepository.findById(avaliacao.getIdUserAvaliando()).get();
            userAvaliadoExists = appUserRepository.findById(avaliacao.getIdUserAvaliado()).get();
        } catch (NoSuchElementException ex) {
            return "Não existe tal usuário";
        }
        avaliacao.setUsuarioAvaliando(userAvaliandoExists);
        avaliacao.setUsuarioAvaliado(userAvaliadoExists);
        try {
            if (avaliacao.getTextoAvaliacao().length() > 255) {
                return "Texto de avaliação maior que 255 caracteres.";
            } else if (avaliacao.getNota() > 5 || avaliacao.getNota() < 0) {
                return "A nota deve ser entre 0 e 5.";
            }
            avaliacaoRepository.save(avaliacao);
            return "Avaliação cadastrada com sucesso";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Erro ao cadastrar avaliação";
    }

    public List<Avaliacao> listarAvaliacoesPorIDAvaliando(Long id){
        return avaliacaoRepository.findAllByIdAvaliando(id);
    }

    public List<Avaliacao> listarAvaliacoesPorIDAvaliado(Long id){
        return avaliacaoRepository.findAllByIdAvaliado(id);
    }

    public List<Avaliacao> listarTodos(){
        return avaliacaoRepository.findAll();
    }

    public String mediaPonderada(Long id){
        AppUser appUserAvaliado;
        try {
            appUserAvaliado = appUserRepository.findById(id).get();
        } catch (NoSuchElementException ex){
            return "Usuário Inxistente!!!";
        }

        List<Avaliacao> listaAvaliacoes = avaliacaoRepository.findAllByIdAvaliado(id);
        Double soma = 0.0;
        Double somaPesos = 0.0;
        Double nota;

        for (Avaliacao avaliacao : listaAvaliacoes) {
            if(avaliacao.getUsuarioAvaliando().getAppUserRole() == AppUserRole.VIP){
                soma += avaliacao.getNota() * 2;
                somaPesos += 2;
            }
            else{
                soma += avaliacao.getNota();
                somaPesos++;
            }
        }

        try {
            nota = soma / somaPesos;
        } catch (ArithmeticException ex){
            return "erro: Divisão por zero";
        }

        return appUserAvaliado.getFirstName()
                + "\n" + appUserAvaliado.getEmail()
                + "\n nota: " + nota;
    }

}
