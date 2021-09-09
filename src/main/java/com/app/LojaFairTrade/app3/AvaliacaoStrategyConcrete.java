package com.app.LojaFairTrade.app3;

import com.app.LojaFairTrade.framework.entity.AppUser;
import com.app.LojaFairTrade.framework.entity.AppUserRole;
import com.app.LojaFairTrade.framework.entity.Avaliacao;
import com.app.LojaFairTrade.framework.entity.Produto;
import com.app.LojaFairTrade.framework.repository.AppUserRepository;
import com.app.LojaFairTrade.framework.repository.AvaliacaoRepository;
import com.app.LojaFairTrade.framework.repository.ProdutoRepository;
import com.app.LojaFairTrade.framework.service.AvaliacaoStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AvaliacaoStrategyConcrete implements AvaliacaoStrategy {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AppUserRepository appUserRepository;

    private final ProdutoRepository produtoRepository;

    public String adicionarAvaliacao(Avaliacao avaliacao) {
        AppUser userAvaliandoExists;
        Object objectAvaliadoExists;
        try {
            userAvaliandoExists = appUserRepository.findById(avaliacao.getIdUserAvaliando()).get();
            objectAvaliadoExists = produtoRepository.findById(avaliacao.getIdObjectAvaliado()).get();
        } catch (NoSuchElementException ex) {
            return "Não existe tal usuário";
        }
        avaliacao.setUsuarioAvaliando(userAvaliandoExists);
        avaliacao.setObjectAvaliado((Produto) objectAvaliadoExists);
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

    public Object nota(Long id){
        Produto produtoAvaliado = produtoRepository.findById(id).get();
        float nota = calcularNota(id, AppUserRole.USER);
        float notaCritica = calcularNota(id, AppUserRole.CRITIC);

        ProdutoNota prodNota = new ProdutoNota(produtoAvaliado, nota, notaCritica);
        return prodNota;
    }

    public Object rankCritica(){
        List<Produto> listAvaliados = produtoRepository.findAll();
        SortedMap<Double, List<Produto>> rank = new TreeMap<>();

        for (Produto prodAvaliado : listAvaliados) {
            Double notaCritica = (double) calcularNota(prodAvaliado.getId(), AppUserRole.CRITIC);
            if(!notaCritica.isNaN()) {
                if(!rank.containsKey(notaCritica)) {
                    List<Produto> lProduto = new ArrayList<>();
                    rank.put(notaCritica, lProduto);
                    rank.get(notaCritica).add(prodAvaliado);
                }
                else
                    rank.get(notaCritica).add(prodAvaliado);
            }
        }

        String ranked = "";
        while(!rank.isEmpty()){
            for (Produto produto : rank.get(rank.lastKey())) {
                ranked += "Nota: " + rank.lastKey() + "\t Filme: " + produto.getNome() + "\n";
            }
            rank.remove(rank.lastKey());
        }
        return ranked;
    }

    public float calcularNota(Long id, AppUserRole auRole){
        List<Avaliacao> listaAvaliacoes = avaliacaoRepository.findAllByIdAvaliado(id);
        float soma = 0;
        float n = 0;

        for (Avaliacao avaliacao : listaAvaliacoes) {
            if(avaliacao.getUsuarioAvaliando().getAppUserRole() == auRole) {
                soma += avaliacao.getNota();
                n++;
            }
        }

        return soma / n;
    }

}
