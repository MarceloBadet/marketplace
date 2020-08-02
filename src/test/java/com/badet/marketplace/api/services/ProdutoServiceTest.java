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
	
	@After
	public final void tearDown() {
		this.produtoRepository.deleteAll();
		this.categoriaRepository.deleteAll();
	}	
	
	//Verifica a inserção
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
	
	//Verifica a alteração
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
	
	//Verificar a exceção lançada quando a categoria não é encontrada
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
	
	//Verificar consulta por Id
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
	
	//Verificar a exceção lançada quando o produto não é encontrado
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
	
	//Verificar consulta por Nome
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

	//Verificar a exceção lançada quando a quantidade de caracteres é menor que a esperada
	@Test(expected = BusinessException.class)
	public void testBuscarProdutoPorNome_BusinessException() throws Exception {
		
		try {
			this.produtoService.consultarPorNome("Pr", PageRequest.of(0, this.quantidadePorPagina));
		} catch (BusinessException e) {
			throw e;
		}
	}		

	//Verifica a consulta de todos os produtos
	@Test
	public void testBuscarTodosProduto() {
		
		Page<Produto> listaProdutos = this.produtoService.consultarTodos(PageRequest.of(0, this.quantidadePorPagina));
		
		assertTrue(!listaProdutos.isEmpty());
		assertEquals(3, listaProdutos.toList().size());
	}
	
	//Verifica a remoção do produto
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

	//Valida a excecão de lançada quando o produto não é encontrado
	@Test(expected = BusinessException.class)
	public void testRemoverProduto_BusinessException() throws Exception {
		try {
			this.produtoService.remover(PRODUTO_ID_NAO_CADASTRADO);
		} catch (Exception e) {
			throw e;
		} 
	}

}
