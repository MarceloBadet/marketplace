package com.badet.marketplace.api.job.calculoscore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.badet.marketplace.api.entities.Produto;
import com.badet.marketplace.api.services.ProdutoService;

@Component
public class CalculoScoreJob {
	
	private static final String TODOS_OS_DIAS_DUAS_HORAS_AM = "0 0 2 * * *";
	
	@Autowired
	private ProdutoService produtoService;
	
	@Scheduled(cron = TODOS_OS_DIAS_DUAS_HORAS_AM)
	protected void realizarCalculoScore() {
		List<Produto> listaProdutos = obterTodosProdutos(new ArrayList<Produto>(), 0);
		listaProdutos.stream().parallel().forEach(produtoService::atualizarScoreProduto);
			
	}

	/**
	 * Retorna uma lista contendo todos os produtos da base.
	 * 
	 * @param List<Produto>
	 * @param pagina
	 * @return List<Produto>
	 *   
	 */	
	private List<Produto> obterTodosProdutos(List<Produto> listaProdutos, int pagina){
		
		Page<Produto> listaProdutosPage = produtoService.consultarTodos(PageRequest.of(pagina, 200));
		
		if(listaProdutosPage != null && !listaProdutosPage.isEmpty()) {
			listaProdutos.addAll(listaProdutosPage.toList());
			obterTodosProdutos(listaProdutos, pagina + 1);
		}
		
		return listaProdutos;
	}
	
}
