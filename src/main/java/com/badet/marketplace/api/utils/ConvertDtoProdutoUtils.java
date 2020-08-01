package com.badet.marketplace.api.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.badet.marketplace.api.dtos.AtualizarProdutoDto;
import com.badet.marketplace.api.dtos.CadastroProdutoDto;
import com.badet.marketplace.api.dtos.CategoriaDto;
import com.badet.marketplace.api.dtos.ProdutoConsultaDto;
import com.badet.marketplace.api.dtos.ProdutoDto;
import com.badet.marketplace.api.entities.Categoria;
import com.badet.marketplace.api.entities.Produto;


public class ConvertDtoProdutoUtils {
	
	public static Produto converterDtoParaProduto(CadastroProdutoDto cadastroProdutoDto) {
		Produto produto = new Produto();
		produto.setNome(cadastroProdutoDto.getNome());
		produto.setDescricao(cadastroProdutoDto.getDescricao());;
		Categoria categoria = new Categoria();
		categoria.setId(Long.valueOf(cadastroProdutoDto.getIdCategoria()));
		produto.setCategoria(categoria);
		produto.setDataCriacao(new Date());
		if(cadastroProdutoDto instanceof AtualizarProdutoDto) {
			produto.setScore(0);
			produto.setId(Long.valueOf((((AtualizarProdutoDto)cadastroProdutoDto).getId())));
		}
		return produto;
	}
	
	public static ProdutoDto converterProdutoParaDto(Produto produto) {
		ProdutoDto produtoDto = new ProdutoDto();
		produtoDto.setId(produto.getId());
		produtoDto.setNome(produto.getNome());
		produtoDto.setDescricao(produto.getDescricao());
		CategoriaDto categoria = new CategoriaDto();
		categoria.setId(produto.getCategoria().getId());
		categoria.setNome(produto.getCategoria().getNome());
		produtoDto.setCategoria(categoria);;
		produtoDto.setDataCriacao(produto.getDataCriacao());
		produtoDto.setScore(produto.getScore());
		return produtoDto;
	}
	
	public static ProdutoConsultaDto converterProdutoParaConsultaDto(Produto produto) {
		ProdutoConsultaDto produtoDto = new ProdutoConsultaDto();
		produtoDto.setId(produto.getId());
		produtoDto.setNome(produto.getNome());
		produtoDto.setDescricao(produto.getDescricao());
		produtoDto.setDataCriacao(produto.getDataCriacao());
		produtoDto.setScore(produto.getScore());
		return produtoDto;
	}
	
	public static List<ProdutoConsultaDto> converterProdutoParaListaConsultaDto(List<Produto> listaProdutos) {
		List<ProdutoConsultaDto> listaProdutoConsultaDto = new ArrayList<ProdutoConsultaDto>();

		if(listaProdutos != null) {
			for(Produto produto : listaProdutos) {
				listaProdutoConsultaDto.add(converterProdutoParaConsultaDto(produto));
			}
		}

		return listaProdutoConsultaDto;
	}	
}
