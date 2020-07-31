package com.badet.marketplace.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.badet.marketplace.api.security.entities.Usuario;
import com.badet.marketplace.api.security.enums.PerfilEnum;


public class JwtUserFactory {

	public JwtUserFactory() {
		
	}
	
	/**
	 * Converte e gera um JwtUser com base nos dados de um funcion�rio.
	 * 
	 * @param funcionario
	 * @return JwtUser
	 */	
	public static JwtUser created(Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getLogin(), usuario.getSenha(), 
		mapToGrantedAuthorities(usuario.getPerfil()));
	}
	
	/**
	 * Converte o perfil do usu�rio para o formato utilizado pelo Spring Security.
	 * 
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */	
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}
	
}