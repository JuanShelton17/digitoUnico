package com.juan.inter.digitoUnico.digitoUnico.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.juan.inter.digitoUnico.digitoUnico.controller.UsuarioController;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;
import com.juan.inter.digitoUnico.digitoUnico.repository.UsuarioRepository;
import com.juan.inter.digitoUnico.digitoUnico.service.impl.CriptografiaService;
import com.juan.inter.digitoUnico.digitoUnico.service.impl.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

	private final String BASE_URL = "/usuarios";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CriptografiaService criptografiaService;

	@Mock
	private UsuarioRepository usuarioRepository;

	@MockBean
	private UsuarioServiceImpl usuarioServiceImpl;

	@Test
	void buscarUsuarioPorId() throws Exception {

		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("teste");
		usuario.setEmail("teste@inter.com");

		Optional<Usuario> usuarioOpt = Optional.of(usuario);
		Mockito.when(usuarioRepository.findById(1L)).thenReturn(usuarioOpt);

		mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL, "/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		assertEquals(usuarioRepository.findById(1L), usuarioOpt);
	}

	@Test
	void buscarTodosUsuarios() throws Exception {

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

		mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		assertNotNull(usuarioServiceImpl.buscarTodos());
	}

//	@Test
//	void inserirUsuario() throws Exception {
//
//		UsuarioDto usuarioDto = new UsuarioDto();
//		usuarioDto.setId(1L);
//		usuarioDto.setNome("teste");
//		usuarioDto.setEmail("teste@inter.com");
//		usuarioDto.setDigitoUnicoDtoList(null);
//
//		Usuario usuario = new Usuario();
//		usuario.setId(1L);
//		usuario.setNome("teste");
//		usuario.setEmail("teste@inter.com");
//
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		Mockito.when(usuarioServiceImpl.inserir(usuarioDto)).thenReturn(usuario);
//		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).content(objectMapper.writeValueAsString(usuario))
//				.characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isCreated());
//
//	}
	
//	@Test
//	void excluirUsuario() throws Exception {
//		
//		Usuario usuario = new Usuario();
//		usuario.setId(1L);
//		usuario.setNome("teste");
//		usuario.setEmail("teste@inter.com");
//		
//		Optional<Usuario> usuarioOpt = Optional.of(usuario);
//		Mockito.doNothing().when(usuarioRepository).deleteById(1L);
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL).content(objectMapper.writeValueAsString(usuario)))
//		.andExpect(MockMvcResultMatchers.status().isOk());
//	}
}
