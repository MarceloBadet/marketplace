package com.badet.marketplace.api.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.badet.marketplace.api.security.JwtUserFactory;
import com.badet.marketplace.api.security.entities.Usuario;
import com.badet.marketplace.api.security.service.UsuarioService;

@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioService.buscarPorLogin(username);
		
		if(usuario.isPresent()) {
			return JwtUserFactory.created(usuario.get());
		}
		
		throw new UsernameNotFoundException("Login não encontrado");
	}

}
