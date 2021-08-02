package com.app.LojaFairTrade.entity;

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
public class Avaliacao {
    @Id
    @SequenceGenerator(
            name = "avaliacao_sequence",
            sequenceName = "avaliacao_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "avaliacao_sequence"
    )
    private long id;

    @Transient
    private Long idUser;

    @ManyToOne
    private AppUser usuarioAvaliado;
    private float nota;
    private String textoAvaliacao;

    public Avaliacao(AppUser usuarioAvaliado, float nota, String textoAvaliacao){
        this.usuarioAvaliado = usuarioAvaliado;
        this.nota = nota;
        this.textoAvaliacao = textoAvaliacao;
    }

    public Long getId(){
        return this.id;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getTextoAvaliacao() {
        return textoAvaliacao;
    }

    public void setTextoAvaliacao(String textoAvaliacao) {
        this.textoAvaliacao = textoAvaliacao;
    }
}
