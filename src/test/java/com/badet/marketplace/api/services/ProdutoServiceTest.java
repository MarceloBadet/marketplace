package com.badet.marketplace.api.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.badet.marketplace.api.entities.Categoria;
import com.badet.marketplace.api.entities.Produto;
import com.badet.marketplace.api.exception.BusinessException;
import com.badet.marketplace.api.repositories.CategoriaRepository;
import com.badet.marketplace.api.repositories.ProdutoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProdutoServiceTest {
	
	private static final Integer SCORE_5 = Integer.valueOf(5);
	private static final Long PRODUTO_ID_1 = Long.valueOf(1);
	private static final Long PRODUTO_ID_NAO_CADASTRADO = Long.valueOf(123456);
	
	
	@Value("${paginacao.qtd_por_pagina}")
	private int quantidadePorPagina;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoService produtoService;	
	
	/**
	 * Insere os produtos no banco H2 que serão utilizado no teste unitário
	 * 
	 */	
	@Before
	public void setUp() throws Exception{
		Categoria categoria = new Categoria();
		categoria.setNome("Categoria 02");
		this.categoriaRepository.save(categoria);
		
		Produto produto1 = new Produto();
		produto1.setNome("Produto 1");
		produto1.setDescricao("Descricao Produto 01");
		produto1.setCategoria(categoria);
		produto1.setScore(0);
		produto1.setDataCriacao(new Date());
		this.produtoRepository.save(produto1);
		
		Produto produto2= new Produto();
		produto2.setNome("Produto 2");
		produto2.setDescricao("Descricao Produto 02");
		produto2.setCategoria(categoria);
		produto2.setScore(5);
		produto2.setDataCriacao(new Date());
		this.produtoRepository.save(produto2);
		
		Produto produto3 = new Produto();
		produto3.setNome("Produto 3");
		produto3.setDescricao("Descricao Produto 03");
		produto3.setCategoria(categoria);
		produto3.setScore(0);
		produto3.setDataCriacao(new Date());
		this.produtoRepository.save(produto3);
	}
	
	/**
	 * Apaga todos os produtos do banco H2
	 * 
	 */		
	@After
	public final void tearDown() {
		this.produtoRepository.deleteAll();
		this.categoriaRepository.deleteAll();
	}	
	
	/**
	 * Verifica se o produto esta sendo inserido corretamente.
	 * 
	 */	
	@Test
	public void testPersistirProduto() {
		Produto produtoInsert = null;

		Categoria categoria = new Categoria();
		categoria.setNome("Categoria 02");
		this.categoriaRepository.save(categoria);
		
		Produto produto = new Produto();
		produto.setNome("produto 05");
		produto.setDescricao("Descricao Produto 05");
		produto.setCategoria(categoria);
		produto.setScore(3);
		produto.setDataCriacao(new Date());
		try {
			produtoInsert = this.produtoService.persistir(produto);
		} catch (BusinessException e) {
			e.printStackTrace();
		} 

		assertNotNull(produtoInsert);
	}	
	
	/**
	 * Verifica se o produto esta sendo alterado corretamente.
	 * 
	 */	
	@Test
	public void testPersistirProdutoAlterar() {
		Categoria categoria = new Categoria();
		categoria.setNome("Categoria 02");
		this.categoriaRepository.save(categoria);
		
		Produto produto = new Produto();
		produto.setNome("produto 05");
		produto.setDescricao("Descricao Produto 05");
		produto.setCategoria(categoria);
		produto.setScore(3);
		produto.setDataCriacao(new Date());
		try {
			produto = this.produtoService.persistir(produto);
		} catch (BusinessException e) {
			e.printStackTrace();
		} 
		
		
		Produto produtoAlterar = null;

		try {
			produtoAlterar = this.produtoService.consultarPorId(produto.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		try {
			produtoAlterar.setNome("Alteracao");
			produtoAlterar = this.produtoService.persistir(produtoAlterar);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		assertEquals("Alteracao", produtoAlterar.getNome());
	}		
	
	/**
	 * Verificar se a exceção esta sendo lançada corretamente se a categoria não é encontrada.
	 * 
	 */		
	@Test(expected = BusinessException.class)
	public void testPersistirProduto_BusinessException() throws Exception {
		Categoria categoria = new Categoria();
		categoria.setId(444L);
		
		Produto produto = new Produto();
		produto.setDescricao("Descricao Produto 05");
		produto.setCategoria(categoria);
		produto.setScore(3);
		produto.setDataCriacao(new Date());

		try {
			this.produtoService.persistir(produto);
		} catch (Exception e) {
			throw e;
		} 
	}
	
	/**
	 * Verifica se a consulta por Id esta retornando o produto correto.
	 * 
	 */		
	@Test
	public void testBuscarProdutoPorId() {
		Categoria categoria = new Categoria();
		categoria.setNome("Categoria 02");
		this.categoriaRepository.save(categoria);
		
		Produto produto = new Produto();
		produto.setNome("produto 05");
		produto.setDescricao("Descricao Produto 05");
		produto.setCategoria(categoria);
		produto.setScore(3);
		produto.setDataCriacao(new Date());
		try {
			produto = this.produtoService.persistir(produto);
		} catch (BusinessException e) {
			e.printStackTrace();
		} 
		
		
		Produto produtoConsultar = null;

		try {
			produtoConsultar = this.produtoService.consultarPorId(produto.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		assertEquals(produto.getId(), produtoConsultar.getId());
	}
	
	/**
	 * Verificar se a exceção esta sendo lançada corretamente quando o produto não é encontrado.
	 * 
	 */			
	@Test(expected = BusinessException.class)
	public void testBuscarProdutoPorId_BusinessException() throws Exception {
		
		Produto produto = null;

		try {
			produto = this.produtoService.consultarPorId(PRODUTO_ID_NAO_CADASTRADO);
		} catch (Exception e) {
			throw e;
		}

		assertFalse(produto != null);
	}	
	
	/**
	 * Verifica se a consulta por nome esta retornando a quantidade de produtos e a ordenação esta correta.
	 * 
	 */	
	@Test
	public void testBuscarProdutoPorNome() {
		
		Page<Produto> listaProdutos = null;

		try {
			listaProdutos = this.produtoService.consultarPorNome("Produto", PageRequest.of(0, this.quantidadePorPagina));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		assertTrue(!listaProdutos.isEmpty());
		assertEquals(3, listaProdutos.toList().size());
		
		// verificar se a ordenação por score esta correta
		Produto produto = listaProdutos.iterator().next();
		assertEquals(SCORE_5, produto.getScore());
	}

	/**
	 * Verificar se a exceção está sendo lançada corretamente quando a quantidade de caracteres é menor que a esperada.
	 * 
	 */		
	@Test(expected = BusinessException.class)
	public void testBuscarProdutoPorNome_BusinessException() throws Exception {
		
		try {
			this.produtoService.consultarPorNome("Pr", PageRequest.of(0, this.quantidadePorPagina));
		} catch (BusinessException e) {
			throw e;
		}
	}		

	/**
	 * Verifica se a consulta de todos os produtos esta retornando a quantidade esperada.
	 * 
	 */	
	public void testBuscarTodosProduto() {
		
		Page<Produto> listaProdutos = this.produtoService.consultarTodos(PageRequest.of(0, this.quantidadePorPagina));
		
		assertTrue(!listaProdutos.isEmpty());
		assertEquals(3, listaProdutos.toList().size());
	}
	
	/**
	 * Verifica se o produto esta sendo removido corretamente.
	 * 
	 */		
	@Test
	public void testRemoverProduto() {
		Produto produto = null;

		try {
			this.produtoService.remover(PRODUTO_ID_1);
			
			produto = this.produtoService.consultarPorId(PRODUTO_ID_1);
		} catch (BusinessException e) {
			e.printStackTrace();
		} 

		assertNull(produto);
	}	

	/**
	 * Verifica se a excecão esta sendo lançada corretamente quando o produto não é encontrado.
	 * 
	 */	
	@Test(expected = BusinessException.class)
	public void testRemoverProduto_BusinessException() throws Exception {
		try {
			this.produtoService.remover(PRODUTO_ID_NAO_CADASTRADO);
		} catch (Exception e) {
			throw e;
		} 
	}

}
