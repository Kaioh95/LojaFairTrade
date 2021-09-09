package com.app.LojaFairTrade.app3;

import com.app.LojaFairTrade.framework.entity.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoNota {
    private Produto produtoN;
    private double nota;
    private double notaCritica;

    public ProdutoNota(Produto produtoN, double nota, double notaCritica){
        this.produtoN = produtoN;
        this.nota = nota;
        this.notaCritica = notaCritica;
    }
}
