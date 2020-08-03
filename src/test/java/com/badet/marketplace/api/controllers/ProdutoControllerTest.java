package com.badet.marketplace.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.badet.marketplace.api.dtos.AtualizarProdutoDto;
import com.badet.marketplace.api.dtos.CadastroProdutoDto;
import com.badet.marketplace.api.entities.Categoria;
import com.badet.marketplace.api.entities.Produto;
import com.badet.marketplace.api.exception.BusinessException;
import com.badet.marketplace.api.services.ProdutoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProdutoControllerTest {

	private static final Long PRODUTO_ID_1 = Long.valueOf(1);
	private static final Long PRODUTO_INVALIDO = Long.valueOf(1111111);
	private static final String PRODUTO_NOME_CONSULTA = "Produto";

	private static final String BUSCAR_PRODUTO_ID_URL = "/api/produto/consultar/";
	private static final String BUSCAR_PRODUTO_NOME_URL = "/api/produto/consultar/nome/";
	private static final String BUSCAR_TODOS_PRODUTOS_URL = "/api/produto/consultar";
	private static final String CADASTRAR_PRODUTO_URL = "/api/produto/cadastrar";
	private static final String ATUALIZAR_PRODUTO_URL = "/api/produto/atualizar";
	private static final String REMOVER_PRODUTO_URL = "/api/produto/remover/";	

	
	@Value("${paginacao.qtd_por_pagina}")
	private int quantidadePorPagina;	
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ProdutoService produtoService;
	
	/**
	 * Retorna o objeto Produto populado.
	 * 
	 */		
	private Produto obterDadosProduto() {
		Categoria categoria = new Categoria();
		categoria.setId(1L);
		categoria.setNome("Categoria 01");
		
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNome("Produto 1");
		produto.setDescricao("Teste Descricao Produto 01");
		produto.setCategoria(categoria);
		produto.setScore(0L);
		produto.setDataCriacao(new Date());
	
		return produto;
	}
	
	/**
	 * Retorna o json do objeto CadastroProdutoDto.
	 * 
	 */			
	private String obterJsonRequisicaoCadastroJson() throws JsonProcessingException {
		CadastroProdutoDto cadastroProdutoDto = new CadastroProdutoDto();
		cadastroProdutoDto.setNome("Produto 1");
		cadastroProdutoDto.setIdCategoria("1");
		cadastroProdutoDto.setDescricao("Teste Descricao Produto 01");

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(cadastroProdutoDto);
	}	
	
	/**
	 * Retorna o json do objeto AtualizarProdutoDto.
	 * 
	 */		
	private String obterJsonRequisicaoAtualizarJson() throws JsonProcessingException {
		AtualizarProdutoDto atualizarProdutoDto = new AtualizarProdutoDto();
		atualizarProdutoDto.setId("1");
		atualizarProdutoDto.setNome("Produto 1");
		atualizarProdutoDto.setDescricao("Teste Descricao Produto 01");
		atualizarProdutoDto.setIdCategoria("1");

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(atualizarProdutoDto);
	}		
	
	/**
	 * Retorna a lista de produtos paginados.
	 * 
	 */		
	private Page<Produto> obterListaDadosProduto() {
		final Page<Produto> page = Mockito.mock(Page.class);
		return page;
	}		

	
	/**
	 * Verifica se o status de retorno Http esta correto quando o produto é cadastrado corretamente.
	 * 
	 */	
	@Test
	@WithMockUser
	public void testCadastrarProdutoValido() throws Exception  {
		Produto produto = obterDadosProduto();
		
		BDDMockito.given(this.produtoService.persistir(Mockito.any(Produto.class))).willReturn(produto);
		
		mvc.perform(MockMvcRequestBuilders.post(CADASTRAR_PRODUTO_URL)
					.content(obterJsonRequisicaoCadastroJson())
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.data.id").value("1"))
					.andExpect(jsonPath("$.data.nome").value("Produto 1"))
					.andExpect(jsonPath("$.data.descricao").value("Teste Descricao Produto 01"))
					.andExpect(jsonPath("$.errors").isEmpty());
	}

	/**
	 * Verifica se o status de retorno Http esta correto quando a categoria informada no cadastro do produto está inválida.
	 * 
	 */			
	@Test
	@WithMockUser
	public void testCadastrarProdutoCategoriaInvalido() throws Exception  {
		BDDMockito.given(this.produtoService.persistir(Mockito.any(Produto.class))).willThrow(new BusinessException("Categoria não foi encontrada."));
		
		mvc.perform(MockMvcRequestBuilders.post(CADASTRAR_PRODUTO_URL)
					.content(obterJsonRequisicaoCadastroJson())
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isUnprocessableEntity())
					.andExpect(jsonPath("$.errors").value("Categoria não foi encontrada."));
	}	
	
	/**
	 * Verifica se o status de retorno Http esta correto quando a consulta por Id retorna um produto.
	 * 
	 */			
	@Test
	@WithMockUser
	public void testAtualizarProdutoValido() throws Exception  {
		Produto produto = obterDadosProduto();
		
		BDDMockito.given(this.produtoService.persistir(Mockito.any(Produto.class))).willReturn(produto);
		
		mvc.perform(MockMvcRequestBuilders.put(ATUALIZAR_PRODUTO_URL)
					.content(obterJsonRequisicaoAtualizarJson())
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.data.id").value("1"))
					.andExpect(jsonPath("$.data.nome").value("Produto 1"))
					.andExpect(jsonPath("$.data.descricao").value("Teste Descricao Produto 01"))
					.andExpect(jsonPath("$.errors").isEmpty());
	}	
	
	
	/**
	 * Verifica se o status de retorno Http esta correto quando o produto é retornado nao consulta por Id.
	 * 
	 */		
	@Test
	@WithMockUser
	public void testBuscarProdutoIdValido() throws Exception  {
		BDDMockito.given(this.produtoService.consultarPorId(Mockito.anyLong())).willReturn(this.obterDadosProduto());
		
		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_PRODUTO_ID_URL + PRODUTO_ID_1)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.data.id").value("1"))
					.andExpect(jsonPath("$.data.nome").value("Produto 1"))
					.andExpect(jsonPath("$.data.descricao").value("Teste Descricao Produto 01"))
					.andExpect(jsonPath("$.errors").isEmpty());
	}	
	
	/**
	 * Verifica se o status de retorno Http esta correto quando na consulta por Id o produto não é encontrado.
	 * 
	 */			
	@Test
	@WithMockUser
	public void testBuscarProdutoIdInvalido() throws Exception  {
		BDDMockito.given(this.produtoService.consultarPorId(Mockito.anyLong())).willThrow(new BusinessException("Produto não foi encontrado."));
		
		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_PRODUTO_ID_URL + PRODUTO_INVALIDO)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isUnprocessableEntity())
					.andExpect(jsonPath("$.errors").value("Produto não foi encontrado."));
	}
	
	/**
	 * Verifica se o status de retorno Http esta correto quando todos os produtos são consultados.
	 * 
	 */			
	@Test
	@WithMockUser
	public void testBuscarTodos() throws Exception  {
		BDDMockito.given(this.produtoService.consultarTodos(PageRequest.of(0, this.quantidadePorPagina))).willReturn(obterListaDadosProduto());
		
		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_TODOS_PRODUTOS_URL)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	/**
	 * Verifica se o status de retorno Http esta correto quando a consulta de nome é executada.
	 * 
	 */				
	@Test
	@WithMockUser
	public void testBuscarProdutoNomeValido() throws Exception  {
		BDDMockito.given(this.produtoService.consultarPorNome("Produto", PageRequest.of(0, this.quantidadePorPagina))).willReturn(obterListaDadosProduto());

		
		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_PRODUTO_NOME_URL + "?nome=" + PRODUTO_NOME_CONSULTA + "&pag=0")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	/**
	 * Verifica se o status de retorno Http esta correto quando ele é removido.
	 * 
	 */		
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})	
	public void testRemoverProdutoValido() throws Exception  {
		BDDMockito.given(this.produtoService.consultarPorId(Mockito.anyLong())).willReturn(new Produto());
		
		mvc.perform(MockMvcRequestBuilders.delete(REMOVER_PRODUTO_URL + PRODUTO_ID_1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	/**
	 * Verifica se o status de retorno Http esta correto quando o produto não é encontrado.
	 * 
	 */	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testRemoverProdutoInvalido() throws Exception  {
		Mockito.doThrow(new BusinessException("Produto não foi encontrado.")).doNothing().when(produtoService).remover(PRODUTO_INVALIDO);
	
		MockHttpServletRequestBuilder post = MockMvcRequestBuilders.delete(REMOVER_PRODUTO_URL + PRODUTO_INVALIDO)
				.accept(MediaType.APPLICATION_JSON);
		mvc.perform(post).andExpect(status().isUnprocessableEntity()).andExpect(jsonPath("$.errors").value("Produto não foi encontrado."));	
	}
	
}
