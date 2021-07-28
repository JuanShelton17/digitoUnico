package com.juan.inter.digitoUnico.digitoUnico.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.juan.inter.digitoUnico.digitoUnico.dto.UsuarioDto;
import com.juan.inter.digitoUnico.digitoUnico.exception.UsuarioNaoEncontradoExeption;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;
import com.juan.inter.digitoUnico.digitoUnico.repository.UsuarioRepository;
import com.juan.inter.digitoUnico.digitoUnico.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Optional<Usuario> buscarPorId(Long id) throws UsuarioNaoEncontradoExeption {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (!usuario.isPresent()) {
			throw new UsuarioNaoEncontradoExeption("Usuário não encontrado");
		}
		return usuario;
	}

	@Override
	public Usuario insertUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = usuarioRepository.save(usuarioDto.toEntity());
		return usuario;
	}

	@Override
	public Optional<Usuario> deletar(Long id) {
		usuarioRepository.deleteById(id);
		return Optional.ofNullable(new Usuario(id));
	}
}
