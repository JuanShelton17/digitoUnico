package com.juan.inter.digitoUnico.digitoUnico.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juan.inter.digitoUnico.digitoUnico.dto.DigitoUnicoDto;
import com.juan.inter.digitoUnico.digitoUnico.exception.UsuarioNaoEncontradoExeption;
import com.juan.inter.digitoUnico.digitoUnico.model.DigitoUnico;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;
import com.juan.inter.digitoUnico.digitoUnico.service.DigitoUnicoService;
import com.juan.inter.digitoUnico.digitoUnico.service.UsuarioService;
import com.juan.inter.digitoUnico.digitoUnico.util.DigitoUtils;

@RestController
@RequestMapping("/digito-unico")
public class DigitoUnicoController {

	private DigitoUnicoService digitoUnicoService;

	private UsuarioService usuarioService;

	public DigitoUnicoController(DigitoUnicoService digitoUnicoService, UsuarioService usuarioService) {
		this.digitoUnicoService = digitoUnicoService;
		this.usuarioService = usuarioService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> calcular(@RequestBody @Valid DigitoUnicoDto digitoUnicoDto) {

		DigitoUnico digitoUnico = digitoUnicoService.calcularDigito(digitoUnicoDto.getNumero(),digitoUnicoDto.getMultiplicador());
		
		try {
			digitoUnico.setUsuario(new Usuario(digitoUnicoDto.getIdUsuario()));
			 gravarDigitoUnicoCasoVinculadoAoUsuario(digitoUnico);
			
		} catch (UsuarioNaoEncontradoExeption ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
		return ResponseEntity.ok(DigitoUtils.toDto(digitoUnico));
	}

	private void gravarDigitoUnicoCasoVinculadoAoUsuario(DigitoUnico digitoUnico) throws UsuarioNaoEncontradoExeption {
		verificaUsuarioValido(digitoUnico.getUsuario().getId());

		if (digitoUnico.getUsuario().getId() != null) {
			digitoUnicoService.salvar(digitoUnico);
		}
	}

	private void verificaUsuarioValido(Long idUsuario) throws UsuarioNaoEncontradoExeption {
		Optional<Usuario> usuario = usuarioService.buscarPorId(idUsuario);
		if (!usuario.isPresent()) {
			throw new UsuarioNaoEncontradoExeption("Não foi encontrado nenhum usuário para o id " + idUsuario);
		}
	}
}
