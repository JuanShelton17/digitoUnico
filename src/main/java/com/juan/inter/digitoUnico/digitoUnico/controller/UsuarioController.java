package com.juan.inter.digitoUnico.digitoUnico.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.inter.digitoUnico.digitoUnico.dto.UsuarioDto;
import com.juan.inter.digitoUnico.digitoUnico.exception.UsuarioNaoEncontradoExeption;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;
import com.juan.inter.digitoUnico.digitoUnico.service.UsuarioService;
import com.juan.inter.digitoUnico.digitoUnico.service.impl.CriptografiaService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	private final CriptografiaService criptografiaService;

	public UsuarioController(UsuarioService usuarioService, CriptografiaService criptografiaService) {
		this.usuarioService = usuarioService;
		this.criptografiaService = criptografiaService;
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity getById(@PathVariable("id") Long id) {
		try {
			Optional<Usuario> usuario = usuarioService.buscarPorId(id);
			return ResponseEntity.ok(usuario.get().toDto());
		} catch (UsuarioNaoEncontradoExeption ex) {
			return ResponseEntity.status(404).body(ex.getMessage());
		}
	}
	
	@GetMapping(produces = "application/json")
    public ResponseEntity<List<UsuarioDto>> getAll() {
        List<Usuario> allUsuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(allUsuarios.stream().map(Usuario::toDto).collect(Collectors.toList()));
    }


	@PostMapping(produces = "application/json")
	public ResponseEntity<?> insert(@RequestBody @Valid UsuarioDto usuarioDto) {
		try {
			Usuario usuario = usuarioService.inserir(usuarioDto);
			return ResponseEntity.created(URI.create(String.format("/usuarios/%s", usuario.getId())))
					.body(usuario.toDto());
		} catch (Exception ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody @Valid UsuarioDto usuarioDto) {
		try {
			Optional<Usuario> usuario = usuarioService.deletar(usuarioDto.getId());
			return ResponseEntity.ok(usuario.get().toDto());
		} catch (Exception ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}
	
	@PostMapping(value = "/criptografar")
	public ResponseEntity<?> criptografar(@RequestBody @Valid UsuarioDto usuarioDto) {
		try {
			Usuario usuario = criptografiaService.criptografar(usuarioDto);
			return ResponseEntity.ok(usuario);
		} catch (Exception ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}
	
//	@RequestMapping(value = "/descriptografar", method = RequestMethod.POST)
//	public ResponseEntity<?> descriptografar(@RequestBody @Valid UsuarioDto usuarioDto) {
//		try {
//			Usuario usuario = criptografiaService.descriptografar(usuarioDto);
//			return ResponseEntity.ok(usuario);
//		} catch (Exception ex) {
//			return ResponseEntity.status(400).body(ex.getMessage());
//		}
//	}
}
