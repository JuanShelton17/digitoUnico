package com.juan.inter.digitoUnico.digitoUnico.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juan.inter.digitoUnico.digitoUnico.dto.UsuarioDto;
import com.juan.inter.digitoUnico.digitoUnico.exception.UsuarioNaoEncontradoExeption;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;
import com.juan.inter.digitoUnico.digitoUnico.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getById(@PathVariable("id") Long id) {
		try {
			Optional<Usuario> usuario = usuarioService.buscarPorId(id);
			return ResponseEntity.ok(usuario.get().toDto());
		} catch (UsuarioNaoEncontradoExeption ex) {
			return ResponseEntity.status(404).body(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody @Valid UsuarioDto usuarioDto) {
		try {
			Usuario usuario = usuarioService.insertUsuario(usuarioDto);
			return ResponseEntity.created(URI.create(String.format("/usuarios/%s", usuario.getId())))
					.body(usuario.toDto());
		} catch (Exception ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}
}
