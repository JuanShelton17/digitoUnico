package com.juan.inter.digitoUnico.digitoUnico.service;

import java.util.Optional;

import com.juan.inter.digitoUnico.digitoUnico.dto.UsuarioDto;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;

public interface UsuarioService {

	Optional<Usuario> buscarPorId(Long id);

	Usuario inserir(UsuarioDto usuarioDto);

	Optional<Usuario> deletar(Long id);
}
