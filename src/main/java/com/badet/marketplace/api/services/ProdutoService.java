package com.badet.marketplace.api.services;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.badet.marketplace.api.entities.Categoria;
import com.badet.marketplace.api.entities.Produto;
import com.badet.marketplace.api.exception.BusinessException;
import com.badet.marketplace.api.repositories.CategoriaRepository;
import com.badet.marketplace.api.repositories.ItemVendaRepository;
import com.badet.marketplace.api.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	private static final Logger log = LoggerFactory.getLogger(ProdutoService.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ItemVendaRepository itemVendaRepository;
	
	/**
	 * Cadastrar o produto
	 * 
	 * @param produto 
	 * @return produto
	 * @throws BusinessException 
	 */
	public Produto persistir(Produto produto) throws BusinessException {
		log.info("Persistindo um produto {} ", produto);

		Optional<Categoria> optionalCategoria = categoriaRepository.findById(produto.getCategoria().getId());
	
		if(!optionalCategoria.isPresent()) {
			throw new BusinessException("Categoria não foi encontrada.");	
		}
		
		produto.setCategoria(optionalCategoria.get());

		if(produto.getId() != null) {
			Optional<Produto> optionalProduto = produtoRepository.findById(produto.getId());
			
			if(!optionalProduto.isPresent()) {
				throw new BusinessException("Produto não foi encontrado.");			
			}
		}else {
			produto.setDataCriacao(new Date());
		}	
		
		return produtoRepository.save(produto);
	}
	
	/**
	 * Remove um produto.
	 * 
	 * @param idProduto
	 * @return 
	 * @throws BusinessException 
	 */
	public void remover(Long idProduto) throws BusinessException {
		log.info("Removendo um produto {} ", idProduto);
		Optional<Produto> retorno = produtoRepository.findById(idProduto); 
		
		if(itemVendaRepository.existsByProduto(idProduto)) {
			throw new BusinessException("Produto não pode ser removido.");	
		}
		
		if(retorno.isPresent()) {
			produtoRepository.delete(retorno.get());
		}else {
			throw new BusinessException("Produto não foi encontrado.");			
		}
	}
	
	/**
	 * Retorna um produto de acordo com o id informado.
	 * 
	 * @param idProduto 
	 * @return Produto
	 * @throws CpfInvalidoException 
	 * @throws CpfNaoEncontradoException 
	 */
	public Produto consultarPorId(Long idProduto) throws BusinessException {
		log.info("Buscando um produto {} ", idProduto);
		Optional<Produto> retorno = produtoRepository.findById(idProduto); 
		
		if(retorno.isPresent()) {
			return retorno.get();
		}else {
			throw new BusinessException("Produto não foi encontrado.");			
		}
	}
	
	/**
	 * Retorna uma lista contendo todos os produtos da base.
	 * 
	 * @param 
	 * @return List<Produto>
	 */
	public Page<Produto> consultarPorNomeOrdenado(String nomeProduto, Pageable pageable) {
		log.info("Buscando todos os produtos, filtrados pelo.");
		return produtoRepository.findAll(pageable);
	}

	/**
	 * Retorna uma lista contendo todos os produtos da base.
	 * 
	 * @param 
	 * @return List<Produto>
	 */
	public Page<Produto> consultarTodos(Pageable pageable) {
		log.info("Buscando todos os Produto ");
		return produtoRepository.findAll(pageable);
	}
	
}
