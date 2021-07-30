package com.juan.inter.digitoUnico.digitoUnico.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.juan.inter.digitoUnico.digitoUnico.dto.UsuarioDto;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;
import com.juan.inter.digitoUnico.digitoUnico.repository.UsuarioRepository;
import com.juan.inter.digitoUnico.digitoUnico.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UsuarioServiceTest {

	@InjectMocks
	UsuarioServiceImpl usuarioService;

	@Mock
	UsuarioRepository usuarioRepository;

	@Test
	void getAll() throws Exception {
		List<Usuario> usuarios = new ArrayList();
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("teste");
		usuario.setEmail("teste@inter.com");
		
		Usuario usuario2 = new Usuario();
		usuario.setId(1L);
		usuario.setNome("teste2");
		usuario.setEmail("teste2@inter.com");
		
		usuarios.add(usuario2);
		usuarios.add(usuario);
		
		Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);
		var teste = usuarioService.buscarTodos();
		
		assertTrue(!teste.isEmpty());
		assertNotNull(usuarioService.buscarTodos());
		
	}
	
	@Test
	public void buscarPorId() throws Exception {
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("teste");
		usuario.setEmail("teste@inter.com");
		
		Optional<Usuario> usuarioOpt = Optional.of(usuario);
		Mockito.when(usuarioRepository.findById(1L)).thenReturn(usuarioOpt);
		var teste = usuarioService.buscarPorId(1L);
		assertEquals(teste, usuarioOpt);
	}

	@Test
	void inserir() throws Exception {
		
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(1L);
		usuarioDto.setNome("teste");
		usuarioDto.setEmail("teste@inter.com");
		usuarioDto.setDigitoUnicoDtoList(null);
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("teste");
		usuario.setEmail("teste@inter.com");
		
		Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);
		var teste = usuarioService.inserir(usuarioDto);
		assertEquals(teste, usuario);
	}
	

	@Test
	void deletar() throws Exception {

		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("teste");
		usuario.setEmail("teste@inter.com");
		
		Optional<Usuario> usuarioOpt = Optional.of(usuario);
		Mockito.doNothing().when(usuarioRepository).deleteById(1L);
		var teste = usuarioService.deletar(usuario.getId());
		assertEquals(teste.get().getId(), usuarioOpt.get().getId());
	}
}
