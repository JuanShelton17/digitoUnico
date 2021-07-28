package com.juan.inter.digitoUnico.digitoUnico.util;

import java.util.ArrayList;
import java.util.List;

import com.juan.inter.digitoUnico.digitoUnico.dto.DigitoUnicoDto;
import com.juan.inter.digitoUnico.digitoUnico.model.DigitoUnico;

public class DigitoUtils {

	public static String aplicarMultiplicador(String numero, Integer multiplicador) {
		StringBuilder numeroMultiplicado = new StringBuilder();

		for (int i = 0; i < multiplicador; i++) {
			numeroMultiplicado.append(numero);
		}
		return numeroMultiplicado.toString();
	}

	public static String calcularDigitoUnico(String numero) {
		Integer somaNumeros = 0;

		if (numero.length() == 1) {
			return numero;
		}

		for (char num : numero.toCharArray()) {
			somaNumeros += Integer.parseInt(Character.toString(num));
		}

		if (somaNumeros.toString().length() > 1) {
			return calcularDigitoUnico(somaNumeros.toString());
		}

		return somaNumeros.toString();
	}
	
	public static DigitoUnicoDto toDto(DigitoUnico digitoUnico) {
		DigitoUnicoDto digitoUnicoDto = new DigitoUnicoDto();
		digitoUnicoDto.setId(digitoUnico.getId());
		digitoUnicoDto.setNumero(digitoUnico.getNumero());
		digitoUnicoDto.setMultiplicador(digitoUnico.getMultiplicador());
		digitoUnicoDto.setResultado(digitoUnico.getResultado());
		digitoUnicoDto.setIdUsuario(digitoUnico.getUsuario().getId());
		return digitoUnicoDto;
	}
	
	public static List<DigitoUnicoDto> toListDto(List<DigitoUnico> digitoUnicoList){
        if(digitoUnicoList == null){
            return new ArrayList<>();
        }
        List<DigitoUnicoDto> digitoUnicoDtos = new ArrayList<>();
        for(DigitoUnico digitoUnico : digitoUnicoList){
            digitoUnicoDtos.add(toDto(digitoUnico));
        }

        return digitoUnicoDtos;
    }
}
