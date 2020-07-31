package com.badet.marketplace.api.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badet.marketplace.api.security.entities.Usuario;
import com.badet.marketplace.api.security.repositories.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> buscarPorLogin(String login) {
		return Optional.ofNullable(this.usuarioRepository.findByLogin(login));
	}
}
