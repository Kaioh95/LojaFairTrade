package com.app.LojaFairTrade.repository;

import com.app.LojaFairTrade.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    /*@Query(value = "SELECT p FROM Produto p WHERE p.nome ILIKE '%' || ?1 || '%'",
    nativeQuery = true)*/
    @Query("SELECT p FROM Produto p WHERE LOWER(p.nome) LIKE CONCAT('%', LOWER(?1), '%')")
    List<Produto> findByNome(String nome);


    @Query("SELECT p FROM Produto p ORDER BY p.preco ASC")
    List<Produto> ordernarPorPreco();
}
