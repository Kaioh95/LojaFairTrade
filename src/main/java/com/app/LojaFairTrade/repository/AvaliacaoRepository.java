package com.app.LojaFairTrade.repository;

import com.app.LojaFairTrade.entity.Avaliacao;
import com.app.LojaFairTrade.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a WHERE a.usuarioAvaliado = ?1")
    List<Avaliacao> findAllById(Long idUsuarioAvaliado);
}
