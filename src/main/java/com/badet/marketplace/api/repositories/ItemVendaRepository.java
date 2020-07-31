package com.badet.marketplace.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.badet.marketplace.api.entities.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

	@Query(" select case when count(i) > 0 then true else false end from ItemVenda i where i.produto.id = :idProduto ")
	Boolean existsByProduto(@Param("idProduto") Long idProduto);

}
