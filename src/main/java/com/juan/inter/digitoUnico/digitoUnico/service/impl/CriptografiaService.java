package com.juan.inter.digitoUnico.digitoUnico.service.impl;

import java.security.KeyPair;

import org.springframework.stereotype.Service;

import com.juan.inter.digitoUnico.digitoUnico.dto.UsuarioDto;
import com.juan.inter.digitoUnico.digitoUnico.exception.CriptografiaException;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;
import com.juan.inter.digitoUnico.digitoUnico.service.UsuarioService;
import com.juan.inter.digitoUnico.digitoUnico.util.CriptografiaUtil;

@Service
public class CriptografiaService {

	private final UsuarioService usuarioService;

	public CriptografiaService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario criptografar(UsuarioDto usuario) throws CriptografiaException {
		
		KeyPair keyPair = CriptografiaUtil.geraChave();
		
		if(usuario.getId() != null)
		{
			String nome = usuario.getNome();
			String email = usuario.getEmail();

			byte[] nomeEncript = nome.getBytes();
			byte[] emailEncript = email.getBytes();
			
			usuario.setNome(CriptografiaUtil.decriptografa(nomeEncript, keyPair.getPrivate()));
			usuario.setEmail(CriptografiaUtil.decriptografa(emailEncript, keyPair.getPrivate()));

			return usuarioService.insertUsuario(usuario);
		}
		
		String nome = usuario.getNome();
		String email = usuario.getEmail();

		usuario.setNome(CriptografiaUtil.criptografa(nome, keyPair.getPublic()).toString());
		usuario.setEmail(CriptografiaUtil.criptografa(email, keyPair.getPublic()).toString());

		return usuarioService.insertUsuario(usuario);
	}

//	public Usuario descriptografar(UsuarioDto usuario) throws CriptografiaException {
//		KeyPair keyPair = CriptografiaUtil.geraChave();
//
//		usuario.setNome(CriptografiaUtil.decriptografa(usuario.getNome().getBytes(), keyPair.getPrivate()));
//		usuario.setEmail(CriptografiaUtil.decriptografa(usuario.getEmail().getBytes(), keyPair.getPrivate()));
//
//		return usuarioService.insertUsuario(usuario);
//	}

}
