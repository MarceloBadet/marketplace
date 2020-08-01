package com.badet.marketplace.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.badet.marketplace.api.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query(value="select p from Produto p where p.nome like %:nome% order by p.score desc, p.categoria.nome, p.nome")
	Page<Produto> consultarPorNomeOrdenado(@Param("nome") String nome, Pageable pageable);

}