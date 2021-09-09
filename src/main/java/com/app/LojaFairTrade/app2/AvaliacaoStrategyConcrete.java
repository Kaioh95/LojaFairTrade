package com.app.LojaFairTrade.app2;

import com.app.LojaFairTrade.framework.entity.AppUser;
import com.app.LojaFairTrade.framework.entity.AppUserRole;
import com.app.LojaFairTrade.framework.entity.Avaliacao;
import com.app.LojaFairTrade.framework.entity.Produto;
import com.app.LojaFairTrade.framework.exceptions.AvaliacaoException;
import com.app.LojaFairTrade.framework.repository.AppUserRepository;
import com.app.LojaFairTrade.framework.repository.AvaliacaoRepository;
import com.app.LojaFairTrade.framework.repository.ProdutoRepository;
import com.app.LojaFairTrade.framework.service.AvaliacaoStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AvaliacaoStrategyConcrete implements AvaliacaoStrategy {

    private final AvaliacaoRepository avaliacaoRepository;
    private final ProdutoRepository produtoRepository;

    public String adicionarAvaliacao(Avaliacao avaliacao) {
        Produto p;
        try {
            p = produtoRepository.findById(avaliacao.getIdProdutoAvaliado()).get();
        } catch (NoSuchElementException ex) {
            return "Não existe tal produto";
        }

        avaliacao.setProduto(p);
        try {
            if (avaliacao.getTextoAvaliacao().length() > 255) {
                throw new AvaliacaoException("Texto de avaliação maior que 255 caracteres.");
            } else if (avaliacao.getNota() > 5 || avaliacao.getNota() < 0) {
                throw new AvaliacaoException("A nota deve ser entre 0 e 5.");
            }
            avaliacaoRepository.save(avaliacao);
            return "Avaliação cadastrada com sucesso";
        } catch (AvaliacaoException ex) {
            ex.printStackTrace();
            return "Erro ao cadastrar avaliação";
        }
    }

    public List<Avaliacao> listarTodos(){
        return avaliacaoRepository.findAll();
    }

    public List<Avaliacao> listarAvaliacoesProduto(Long id){
        return avaliacaoRepository.findAllByIdProduto(id);
    }

    public String mediaAritmetica(Long id){
        Produto produtoAvaliado;
        try {
            produtoAvaliado = produtoRepository.findById(id).get();
        } catch (NoSuchElementException ex){
            return "Produto inexistente.";
        }

        List<Avaliacao> listaAvaliacoes = avaliacaoRepository.findAllByIdProduto(id);
        Double soma = 0.0;
        Double nota;

        for (Avaliacao avaliacao : listaAvaliacoes) {
            soma += avaliacao.getNota();
        }

        try {
            nota = soma / listaAvaliacoes.size();
        } catch (ArithmeticException ex){
            return "Erro: divisão por zero";
        }

        return produtoAvaliado.getNome()
                + "\n" + produtoAvaliado.getDescricao()
                + "\n Nota: " + nota;
    }

}
