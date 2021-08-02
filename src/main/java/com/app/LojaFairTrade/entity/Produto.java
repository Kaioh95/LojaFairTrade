package com.app.LojaFairTrade.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;

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

    public Produto(String nome, ProdutoCategoria categoria, String descricao, float preco,
                   float desconto, AppUser usuarioProduto){
        this.categoria = categoria;
        this.nome = nome;
        this.desconto = desconto;
        this.descricao = descricao;
        this.preco = preco;

        this.usuarioProduto = usuarioProduto;
    }

    public String getNome(){
        return this.nome;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public float getPreco(){
        return this.preco;
    }

    public float getDesconto(){
        return this.desconto;
    }

    public ProdutoCategoria getCategoria(){
        return this.categoria;
    }

    public float getPrecoComDesconto() {
        return preco - preco * desconto;
    }
}
