package com.badet.marketplace.api.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badet.marketplace.api.dtos.AtualizarProdutoDto;
import com.badet.marketplace.api.dtos.CadastroProdutoDto;
import com.badet.marketplace.api.dtos.ProdutoDto;
import com.badet.marketplace.api.entities.Produto;
import com.badet.marketplace.api.exception.BusinessException;
import com.badet.marketplace.api.response.Response;
import com.badet.marketplace.api.services.ProdutoService;
import com.badet.marketplace.api.utils.ConvertDtoProdutoUtils;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {

	private static final Logger log = LoggerFactory.getLogger(ProdutoController.class);
	
	@Autowired
	private ProdutoService produtoService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int quantidadePorPagina;

	/**
	 * Cadastrar um produto
	 * 
	 * @param cadastroProdutoDto
	 * @param result
	 * @return ResponseEntity<Response<ProdutoDto>>
	 */
	@PostMapping("/cadastrar")
	public ResponseEntity<Response<ProdutoDto>> cadastrar(@Valid @RequestBody CadastroProdutoDto cadastroProdutoDto, 
			BindingResult result) {
		log.info("Cadastrando Produto {} ", cadastroProdutoDto);
		Response<ProdutoDto> response = new Response<ProdutoDto>();
		
		if(result.hasErrors()) {
			log.info("Erro validando dados de cadastro do produto {} ", result.getAllErrors());			
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Produto produto = ConvertDtoProdutoUtils.converterDtoParaProduto(cadastroProdutoDto);
		
		try {
			produto = this.produtoService.persistir(produto);
		} catch (BusinessException e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.unprocessableEntity().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(ConvertDtoProdutoUtils.converterProdutoParaDto(produto));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Atualizar um produto
	 * 
	 * @param atualizarProdutoDto
	 * @param result
	 * @return ResponseEntity<Response<ProdutoDto>>
	 */
	@PutMapping("/atualizar")
	public ResponseEntity<Response<ProdutoDto>> cadastrar(@Valid @RequestBody AtualizarProdutoDto atualizarProdutoDto, 
			BindingResult result) {
		log.info("Atualizando Produto {} ", atualizarProdutoDto);
		Response<ProdutoDto> response = new Response<ProdutoDto>();
		
		if(result.hasErrors()) {
			log.info("Erro validando dados de atualização do produto {} ", result.getAllErrors());			
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Produto produto = ConvertDtoProdutoUtils.converterDtoParaProduto(atualizarProdutoDto);
		
		try {
			produto = this.produtoService.persistir(produto);
		} catch (BusinessException e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.unprocessableEntity().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(ConvertDtoProdutoUtils.converterProdutoParaDto(produto));
		return ResponseEntity.ok(response);
	}	
	
	/**
	 * Remover um produto
	 * 
	 * @param idProduto
	 * @return ResponseEntity<Response>
	 */	
	@DeleteMapping(value = "/remover/{idProduto}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity remover(@PathVariable("idProduto") Long idProduto) {
		log.info("Removendo produto {} ", idProduto);
		
		Response response = new Response();
		
		try {
			produtoService.remover(idProduto);
		} catch (BusinessException e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.unprocessableEntity().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return new ResponseEntity(HttpStatus.OK);
	}
}
