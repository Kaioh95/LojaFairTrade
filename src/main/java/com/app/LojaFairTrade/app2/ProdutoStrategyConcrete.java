package com.app.LojaFairTrade.app2;

import com.app.LojaFairTrade.framework.entity.AppUser;
import com.app.LojaFairTrade.framework.entity.AppUserRole;
import com.app.LojaFairTrade.framework.entity.Produto;
import com.app.LojaFairTrade.framework.entity.ProdutoCategoria;
import com.app.LojaFairTrade.framework.repository.AppUserRepository;
import com.app.LojaFairTrade.framework.repository.ProdutoRepository;
import com.app.LojaFairTrade.framework.service.ProdutoStrategy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import org.json.JSONObject;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ProdutoStrategyConcrete implements ProdutoStrategy {

    private final ProdutoRepository produtoRepository;
    private final AppUserRepository appUserRepository;
    @Autowired
    private WebClient webClient;

    public String adicionarProduto(Produto produto){
        AppUser userExists;
        try{
            userExists = appUserRepository.findById(produto.getIdUser()).get();
            if(userExists.getAppUserRole() != AppUserRole.ADMIN)
                throw new IllegalAccessException();
        }catch(NoSuchElementException ex){
            return "Não existe tal usuário";
        }catch(IllegalAccessException ex){
            return "Usuário sem permissão para cadastrar um novo produto.";
        }

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

    public List<Produto> produtosIntervaloPreco(float p1, float p2){
        return produtoRepository.produtosIntervaloPreco(p1, p2);
    }

    public List<Produto> todosProdutos(){
        return produtoRepository.findAll();
    }

    public List<Produto> buscarCategoria(ProdutoCategoria categoria){
        return produtoRepository.findByCategoria(categoria);
    }

    public String calcularFrete(Long codigoProduto, Long cepDestino){
        Produto produto = produtoRepository.findById(codigoProduto).get();

        Mono<String> monoGoogleMaps = this.webClient.method(HttpMethod.GET).uri("http://maps.googleapis.com/maps/api/distancematrix/json?origins={1}&destinations={2}&mode=driving&language=pt-BR&sensor=false&",
                        produto.getCepOrigem(), cepDestino)
                .retrieve()
                .bodyToMono(String.class);

        monoGoogleMaps.subscribe(sCorreios -> {
            System.out.println("Finalizado");
        });

        String servicoCorreios = monoGoogleMaps.block();

        JSONObject dadosMap = new JSONObject(servicoCorreios);

        //dadosMap.get

        return servicoCorreios;
    }

    public List<Produto> listarProdutosPorUser(Long id){
        return produtoRepository.findByUser(id);
    }
}
