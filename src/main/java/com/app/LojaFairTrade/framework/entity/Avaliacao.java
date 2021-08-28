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
    private Long idUserAvaliando;
    @ManyToOne
    private AppUser usuarioAvaliando;
    @Transient
    private Long idUserAvaliado;
    @ManyToOne
    private AppUser usuarioAvaliado;

    private float nota;
    private String textoAvaliacao;

    public Avaliacao(AppUser usuarioAvaliando, AppUser usuarioAvaliado, float nota, String textoAvaliacao){
        this.usuarioAvaliando = usuarioAvaliando;
        this.usuarioAvaliado = usuarioAvaliado;
        this.nota = nota;
        this.textoAvaliacao = textoAvaliacao;
    }

    public float getNota() {
        return nota;
    }
}
