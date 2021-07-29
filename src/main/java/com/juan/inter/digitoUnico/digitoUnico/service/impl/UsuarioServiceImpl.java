package com.juan.inter.digitoUnico.digitoUnico.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.juan.inter.digitoUnico.digitoUnico.dto.UsuarioDto;
import com.juan.inter.digitoUnico.digitoUnico.exception.UsuarioNaoEncontradoExeption;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;
import com.juan.inter.digitoUnico.digitoUnico.repository.UsuarioRepository;
import com.juan.inter.digitoUnico.digitoUnico.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado!";

	private static final String USUARIOS_NAO_ENCONTRADOS = "O banco de dados não possui usuários ";

	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Optional<Usuario> buscarPorId(Long id) throws UsuarioNaoEncontradoExeption {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (!usuario.isPresent()) {
			throw new UsuarioNaoEncontradoExeption(USUARIO_NAO_ENCONTRADO);
		}
		return usuario;
	}

	@Override
	public List<Usuario> buscarTodos() {
		List<Usuario> usuario = usuarioRepository.findAll();
		if (usuario.isEmpty()) {
			throw new UsuarioNaoEncontradoExeption(USUARIOS_NAO_ENCONTRADOS);
		}
		return usuario;
	}

	@Override
	public Usuario inserir(UsuarioDto usuarioDto) {
		Usuario usuario = usuarioRepository.save(usuarioDto.toEntity());
		return usuario;
	}

	@Override
	public Optional<Usuario> deletar(Long id) {
		usuarioRepository.deleteById(id);
		return Optional.ofNullable(new Usuario(id));
	}
}
