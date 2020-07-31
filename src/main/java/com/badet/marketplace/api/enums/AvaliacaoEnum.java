package com.badet.marketplace.api.enums;

public enum AvaliacaoEnum {
	EXCELENTE(5),
	MUITO_BOM(4),
	BOM(3),
	MEDIANO(2),
	RUIM(1),
	PESSIMO(0);
	
	private Integer codigo;	
	
	AvaliacaoEnum(int codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}
}
