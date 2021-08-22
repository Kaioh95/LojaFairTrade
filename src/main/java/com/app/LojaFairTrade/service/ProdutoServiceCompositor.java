package com.app.LojaFairTrade.service;

import com.app.LojaFairTrade.entity.AppUser;
import com.app.LojaFairTrade.entity.AppUserRole;
import com.app.LojaFairTrade.entity.Produto;
import com.app.LojaFairTrade.entity.ProdutoCategoria;
import com.app.LojaFairTrade.repository.AppUserRepository;
import com.app.LojaFairTrade.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ProdutoServiceCompositor implements ProdutoServiceInterface{

    private final ProdutoRepository produtoRepository;
    private final AppUserRepository appUserRepository;
    @Autowired
    private WebClient webClient;

    public String adicionarProduto(Produto produto){
        AppUser userExists;
        try{
            userExists = appUserRepository.findById(produto.getIdUser()).get();
        }catch(NoSuchElementException ex){
            return "Não existe tal usuário";
        }

        /*try {
            if (userExists.getAppUserRole() != AppUserRole.SHOP)
                throw new IllegalAccessException();
        }catch(IllegalAccessException e){
            return "Operação Ilegal: Não é possível cadastrar produto";
        }*/

        produto.setUsuarioProduto(userExists);
        try{
            if(produto.getDesconto()<0 || produto.getDesconto() > 1) {
                return "Desconto fora do intervalo aceito [0, 1]";
            }
            produtoRepository.save(produto);
            return "Produto cadastrado com sucesso";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "Erro ao cadastrar produto";
    }

    public List<Produto> pesquisarNome(String nome){
        return produtoRepository.findByNome(nome);
    }

    public List<Produto> compararPrecos(){
        return produtoRepository.ordernarPorPreco();
    }

    public List<Produto> todosProdutos(){
        return produtoRepository.findAll();
    }

    public List<Produto> listarCategoria(ProdutoCategoria categoria){
        return produtoRepository.findByCategoria(categoria);
    }

    public String calcularFrete(Long codigoProduto, Long cepDestino){
        Produto produto = produtoRepository.findById(codigoProduto).get();

        Mono<String> monoCorreios = this.webClient.method(HttpMethod.GET).uri("?nCdEmpresa={1}&sDsSenha={2}&sCepOrigem={3}&sCepDestino={4}&nVlPeso={5}&nCdFormato={6}&nVlComprimento={7}&nVlAltura={8}&nVlLargura={9}&sCdMaoPropria={10}&nVlValorDeclarado={11}&sCdAvisoRecebimento={12}&nCdServico={13}&nVlDiametro={14}&StrRetorno={15}&nIndicaCalculo={16}",
                        null, null, produto.getCepOrigem(), cepDestino, produto.getPeso(), "1", produto.getComprimento(), produto.getAltura(), produto.getLargura(), "n", "0", "n", "04510", "0", "xml", "3")
                .retrieve()
                .bodyToMono(String.class);

        monoCorreios.subscribe(sCorreios -> {
            System.out.println("Finalizado");
        });

        String servicoCorreios = monoCorreios.block();
        return servicoCorreios;
    }
}
