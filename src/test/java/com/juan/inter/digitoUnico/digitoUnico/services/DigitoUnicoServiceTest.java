package com.juan.inter.digitoUnico.digitoUnico.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.juan.inter.digitoUnico.digitoUnico.model.DigitoUnico;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;
import com.juan.inter.digitoUnico.digitoUnico.repository.DigitoUnicoRepository;
import com.juan.inter.digitoUnico.digitoUnico.service.impl.DigitoUnicoServiceImpl;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class DigitoUnicoServiceTest {

	@InjectMocks
	DigitoUnicoServiceImpl digitoUinicoServiceImpl;

	@Mock
	DigitoUnicoRepository digitoUnicoRepository;

	@Test
	void buscarTodos() {
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
		var teste = digitoUinicoServiceImpl.buscarTodos();

		assertTrue(!teste.isEmpty());
		assertNotNull(digitoUinicoServiceImpl.buscarTodos());
	}

	@Test
	void salvarDigito() {
		
		DigitoUnico digito1 = new DigitoUnico();
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("teste");
		usuario.setEmail("teste@inter.com");
		
		digito1.setId(1L);
		digito1.setNumero(123);
		digito1.setMultiplicador(3);
		digito1.setResultado(null);
		digito1.setUsuario(usuario);
		
		Mockito.when(digitoUnicoRepository.save(Mockito.any())).thenReturn(digito1);
		var teste = digitoUinicoServiceImpl.salvar(digito1);
		assertEquals(teste, digito1);
	}
	
	@Test
	void calcularDigito() {
		
		DigitoUnico digito1 = new DigitoUnico();
		
		
		digito1.setId(null);
		digito1.setNumero(877);
		digito1.setMultiplicador(1);
		digito1.setResultado("4");
		digito1.setUsuario(null);
		
		var test = digitoUinicoServiceImpl.calcularDigito(877, 1);
		assertEquals(test.id, digito1.id);
		assertEquals(test.multiplicador, digito1.multiplicador);
		assertEquals(test.resultado, digito1.resultado);
		assertEquals(test.numero, digito1.numero);
		assertEquals(test.usuario, digito1.usuario);
	}

}
