package com.badet.marketplace.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badet.marketplace.api.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
