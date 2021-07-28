package com.juan.inter.digitoUnico.digitoUnico.service.impl;

import org.springframework.stereotype.Service;

import com.juan.inter.digitoUnico.digitoUnico.model.DigitoUnico;
import com.juan.inter.digitoUnico.digitoUnico.repository.DigitoUnicoRepository;
import com.juan.inter.digitoUnico.digitoUnico.service.DigitoUnicoService;
import com.juan.inter.digitoUnico.digitoUnico.util.DigitoUtils;

@Service
public class DigitoUnicoServiceImpl implements DigitoUnicoService {

	private DigitoUnicoRepository digitoUnicoRepository;
	

	public DigitoUnicoServiceImpl(DigitoUnicoRepository digitoUnicoRepository) {
		this.digitoUnicoRepository = digitoUnicoRepository;
	}

	@Override
	public DigitoUnico salvar(DigitoUnico digitoUnico) {
		return digitoUnicoRepository.save(digitoUnico);
	}

	@Override
	public DigitoUnico calcularDigito(Integer numero, Integer multiplo) {
		String valorACalcular = numero.toString();

		if (!multiplo.toString().isEmpty()) {
			valorACalcular = DigitoUtils.aplicarMultiplicador(valorACalcular, multiplo);
		}
		return new DigitoUnico(numero, multiplo, DigitoUtils.calcularDigitoUnico(valorACalcular));
	}
}
