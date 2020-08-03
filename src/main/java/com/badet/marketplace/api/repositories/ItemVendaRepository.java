package com.badet.marketplace.api.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.badet.marketplace.api.entities.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

	@Query(" select case when count(i) > 0 then true else false end from ItemVenda i where i.produto.id = :idProduto ")
	Boolean existsByProduto(@Param("idProduto") Long idProduto);

	@Query(value="select round(sum(t.qtd)/count(t.data_venda))  "
			   + "  from ( "
			   + "        select v.data_venda, count(i.id) as qtd "
			   + " 			from item_venda as i  "
			   + "			join venda as v on i.id_venda = v.id "
			   + " 		   where i.id_produto = :idProduto "
			   + "			group by v.data_venda"
			   + "		) as t", nativeQuery = true)	
	Long consultarVendasPorDia(@Param("idProduto") Long idProduto);
	
	@Query(value="SELECT round(sum(i.avaliacao)/count(*)) "
			   + "  FROM item_venda as i "
			   + "  join venda as v on i.id_venda = v.id "
			   + " where i.id_produto = :idProduto "
			   + "   and i.avaliacao is not null  "
			   + "   and v.data_venda between :dataInicial and :dataFinal", nativeQuery = true)	
	Long consultarMediaAvaliacao(@Param("idProduto") Long idProduto, @Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);
}