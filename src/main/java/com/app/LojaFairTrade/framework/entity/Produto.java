package com.app.LojaFairTrade.framework.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Produto {
    @Id
    @SequenceGenerator(
            name = "produto_sequence",
            sequenceName = "produto_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "produto_sequence"
    )
    private long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private ProdutoCategoria categoria;
    private String descricao;
    private float preco;
    private float desconto; // Intervalo [0, 1]

    @Transient
    private Long idUser;

    @ManyToOne
    private AppUser usuarioProduto;

    @Transient
    private float precoComDesconto;

    private Long cepOrigem;
    private float peso;
    private float comprimento;
    private float altura;
    private float largura;

    public Produto(String nome, ProdutoCategoria categoria, String descricao, float preco,
                   float desconto, AppUser usuarioProduto){
        this.categoria = categoria;
        this.nome = nome;
        this.desconto = desconto;
        this.descricao = descricao;
        this.preco = preco;

        this.usuarioProduto = usuarioProduto;
    }

    public Produto(String nome, ProdutoCategoria categoria, String descricao, float preco,
                   float desconto, AppUser usuarioProduto, Long cepOrigem, float peso,
                   float comprimento, float altura, float largura){
        this.categoria = categoria;
        this.nome = nome;
        this.desconto = desconto;
        this.descricao = descricao;
        this.preco = preco;
        this.cepOrigem = cepOrigem;
        this.peso = peso;
        this.comprimento = comprimento;
        this.altura = altura;
        this.largura = largura;

        this.usuarioProduto = usuarioProduto;
    }

    public float getPrecoComDesconto() {
        return preco - preco * desconto;
    }
}
