package com.badet.marketplace.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Optional;

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
import com.badet.marketplace.api.services.ProdutoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProdutoRepositoryTest {
	
	private static final Integer SCORE_5 = Integer.valueOf(5);
	private static final Long PRODUTO_ID_1 = Long.valueOf(1);
	
	@Value("${paginacao.qtd_por_pagina}")
	private int quantidadePorPagina;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	

	@Before
	public void setUp() throws Exception{
		Categoria categoria = new Categoria();
		categoria.setNome("Categoria 01");
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
	
	@Test
	public void testBuscarPorId() {
		Optional<Produto> optionalproduto = this.produtoRepository.findById(PRODUTO_ID_1);
		
		assertEquals(PRODUTO_ID_1, optionalproduto.get().getId());
	}
	
	@Test
	public void testBuscarPorNome() {
		Page<Produto> listaProdutos = this.produtoRepository.consultarPorNome("Produto", PageRequest.of(0, this.quantidadePorPagina));
		
		assertTrue(!listaProdutos.isEmpty());
		assertEquals(3, listaProdutos.toList().size());
		
		// verificar se a ordenação por score esta correta
		Produto produto = listaProdutos.iterator().next();
		assertEquals(SCORE_5, produto.getScore());
	}
}
