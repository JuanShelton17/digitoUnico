package com.juan.inter.digitoUnico.digitoUnico.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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

import com.juan.inter.digitoUnico.digitoUnico.model.DigitoUnico;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;
import com.juan.inter.digitoUnico.digitoUnico.repository.DigitoUnicoRepository;
import com.juan.inter.digitoUnico.digitoUnico.service.UsuarioService;
import com.juan.inter.digitoUnico.digitoUnico.service.impl.DigitoUnicoServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(DigitoUnicoController.class)
public class DigitoUnicoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DigitoUnicoServiceImpl digitoUnicoServiceImpl;
	
	@MockBean
	private UsuarioService usuarioService;

	@Mock
	private DigitoUnicoRepository digitoUnicoRepository;

	private final String BASE_URL = "/digito-unico";
	
	@Test
	void buscarTodosDigitos() throws Exception {
		List<DigitoUnico> digitos = new ArrayList();

		DigitoUnico digito1 = new DigitoUnico();

		DigitoUnico digito2 = new DigitoUnico();

		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("teste");
		usuario.setEmail("teste@inter.com");

		digito1.setId(1L);
		digito1.setNumero(123);
		digito1.setMultiplicador(3);
		digito1.setResultado(null);
		digito1.setUsuario(usuario);

		digito2.setId(1L);
		digito2.setNumero(321);
		digito2.setMultiplicador(3);
		digito2.setResultado(null);
		digito2.setUsuario(usuario);

		digitos.add(digito1);
		digitos.add(digito2);

		Mockito.when(digitoUnicoRepository.findAll()).thenReturn(digitos);
		mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		assertNotNull(digitoUnicoServiceImpl.buscarTodos());
	}
	
	@Test
	void calcularDigito() throws Exception {
		
	}
}
