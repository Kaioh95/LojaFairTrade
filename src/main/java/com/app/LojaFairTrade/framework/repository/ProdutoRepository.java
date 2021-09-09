package com.app.LojaFairTrade.framework.repository;

import com.app.LojaFairTrade.framework.entity.Produto;
import com.app.LojaFairTrade.framework.entity.ProdutoCategoria;
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

    @Query("SELECT p FROM Produto p WHERE p.preco BETWEEN ?1 AND ?2")
    List<Produto> produtosIntervaloPreco(float p1, float p2);

    @Query("SELECT p FROM Produto p WHERE p.categoria = ?1")
    List<Produto> findByCategoria(ProdutoCategoria categoria);

    @Query("SELECT p FROM Produto p WHERE p.usuarioProduto.id = ?1")
    List<Produto> findByUser(Long id);
}
