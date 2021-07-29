package com.juan.inter.digitoUnico.digitoUnico.services;

import static org.junit.Assert.assertTrue;

import java.security.KeyPair;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.juan.inter.digitoUnico.digitoUnico.util.CriptografiaUtil;

@SpringBootTest
public class CriptografiaUtilTest {

	@Test
	void criptografia() throws Exception {

		try {
			KeyPair keyPair = CriptografiaUtil.geraChave();
			String nome = "teste";
			String email = "teste@teste.com";

			byte[] nomeEncript = CriptografiaUtil.criptografa(nome, keyPair.getPublic());
			byte[] emailEncript = CriptografiaUtil.criptografa(email, keyPair.getPublic());

			String nomeDecpt = CriptografiaUtil.decriptografa(nomeEncript, keyPair.getPrivate());
			String emailDecpt = CriptografiaUtil.decriptografa(emailEncript, keyPair.getPrivate());

			assertTrue(nome.equalsIgnoreCase(nomeDecpt) && email.equalsIgnoreCase(emailDecpt));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
