package com.juan.inter.digitoUnico.digitoUnico.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

import com.juan.inter.digitoUnico.digitoUnico.exception.CriptografiaException;

public class CriptografiaUtil {
	
	public static final String ALGORITMO = "RSA";
	private static final int TAMANHO_ALGORITMO = 2048;
	public static final String ERRO_GERAR_CHAVES = "Erro ao gerar as chave pública e privada.";
	public static final String ERRO_RECUPERACAO = "Erro ao recuperar a chave privada.";
	

	public static KeyPair geraChave() throws CriptografiaException {
		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance(ALGORITMO);
			keyGen.initialize(TAMANHO_ALGORITMO);
		} catch (Exception e) {
			throw new CriptografiaException(ERRO_GERAR_CHAVES);
		}

		return keyGen.generateKeyPair();
	}

	public static byte[] criptografa(String texto, PublicKey chave) throws CriptografiaException {
		byte[] cipherText = null;

		try {
			final Cipher cipher = Cipher.getInstance(ALGORITMO);
			cipher.init(Cipher.ENCRYPT_MODE, chave);
			cipherText = cipher.doFinal(texto.getBytes());
		} catch (Exception e) {
			throw new CriptografiaException(ERRO_GERAR_CHAVES);
		}

		return cipherText;
	}

	public static String decriptografa(byte[] texto, PrivateKey chave) throws CriptografiaException {
		byte[] dectyptedText = null;

		try {
			final Cipher cipher = Cipher.getInstance(ALGORITMO);
			cipher.init(Cipher.DECRYPT_MODE, chave);
			dectyptedText = cipher.doFinal(texto);
		} catch (Exception e) {
			throw new CriptografiaException(ERRO_GERAR_CHAVES);
		}

		return new String(dectyptedText);
	}

	public static PrivateKey getPrivateKey(byte[] encodedPrivateKey) throws CriptografiaException {
		try {
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITMO);
			PrivateKey priKey = keyFactory.generatePrivate(keySpec);
			return priKey;
		} catch (Exception e) {
			throw new CriptografiaException(ERRO_RECUPERACAO);
		}
	}
}
