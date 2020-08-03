package com.badet.marketplace.api.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.badet.marketplace.api.client.NewsApiClient;
import com.badet.marketplace.api.dtos.NoticiaCategoriaDto;

@Service
public class NewsApiClientService {

	@Autowired
	private NewsApiClient newsApiClient;
	
	@Value("${newsapi.uri}")
	private String uri;
	
	@Value("${newsapi.apikey.valor}")
	private String apiKey;	
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Retorna uma lista contendo todos os produtos da base.
	 * 
	 * @param 
	 * @return List<Produto>
	 * @throws URISyntaxException 
	 */
	public NoticiaCategoriaDto obterQuantidadeNoticiasPorCategoria(String categoria, Date dataConsulta) throws URISyntaxException {
		URI baseUri = new URI(uri);
		return newsApiClient.obterQuantidadeNoticiasPorCategoria(baseUri, categoria, dateFormat.format(dataConsulta), apiKey);
	}
	
}
