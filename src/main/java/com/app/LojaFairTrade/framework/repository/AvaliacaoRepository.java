package com.app.LojaFairTrade.framework.repository;

import com.app.LojaFairTrade.framework.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a WHERE a.objectAvaliado.id = ?1")
    List<Avaliacao> findAllByIdAvaliado(Long idUsuarioAvaliado);

    @Query("SELECT a FROM Avaliacao a WHERE a.usuarioAvaliando.id = ?1")
    List<Avaliacao> findAllByIdAvaliando(Long idUsuarioAvaliado);
}
