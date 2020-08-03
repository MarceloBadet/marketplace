package com.badet.marketplace.api.client;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;

import com.badet.marketplace.api.config.JsonClientConfiguration;
import com.badet.marketplace.api.dtos.NoticiaCategoriaDto;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@FeignClient(value = "NewsApi", configuration = JsonClientConfiguration.class)
@Headers({"Accept: " + MediaType.ALL_VALUE, "Content-type: " + MediaType.APPLICATION_JSON_VALUE})
public interface NewsApiClient {
	
	@RequestLine("GET v2/everything?q={categoria}&from={dataConsulta}&sortBy=publishedAt&apiKey={apiKey}")
	NoticiaCategoriaDto obterQuantidadeNoticiasPorCategoria(URI baseUri, @Param("categoria") String categoria, @Param("dataConsulta") String dataConsulta, @Param("apiKey") String apiKey );
}
