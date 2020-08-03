package com.badet.marketplace.api.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badet.marketplace.api.repositories.ItemVendaRepository;

@Service
public class ItemVendaService {


	@Autowired
	private ItemVendaRepository itemVendaRepository;
	
	/**
	 * Retorna uma lista contendo todos os produtos da base.
	 * 
	 * @param 
	 * @return List<Produto>
	 */
	public Long consultarVendasPorDia(Long idProduto) {
		return itemVendaRepository.consultarVendasPorDia(idProduto);
	}
	
	/**
	 * Retorna uma lista contendo todos os produtos da base.
	 * 
	 * @param 
	 * @return List<Produto>
	 */
	public Long consultarMediaAvaliacao(Long idProduto, Date dataInicial, Date dataFinal) {
		return itemVendaRepository.consultarMediaAvaliacao(idProduto, dataInicial, dataFinal);
	}
	
}
