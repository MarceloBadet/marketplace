package com.badet.marketplace.api.exception;

public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 8495468501886996414L;

	public BusinessException(String mensagem) {
		super(mensagem);
	}
}
