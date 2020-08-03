package com.badet.marketplace.api.dtos;

public class NoticiaCategoriaDto {
	
	private String status;
	private Long totalResults;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}
}
