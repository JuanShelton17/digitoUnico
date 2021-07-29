package com.juan.inter.digitoUnico.digitoUnico.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.juan.inter.digitoUnico.digitoUnico.exception.DigitoNaoEncontradoException;
import com.juan.inter.digitoUnico.digitoUnico.model.DigitoUnico;
import com.juan.inter.digitoUnico.digitoUnico.repository.DigitoUnicoRepository;
import com.juan.inter.digitoUnico.digitoUnico.service.DigitoUnicoService;
import com.juan.inter.digitoUnico.digitoUnico.util.DigitoUtils;

@Service
public class DigitoUnicoServiceImpl implements DigitoUnicoService {

	private static final String DIGITOS_NAO_ENCONTRADOS = "O banco de dados não possui dígitos únicos ";
	
	private DigitoUnicoRepository digitoUnicoRepository;
	

	public DigitoUnicoServiceImpl(DigitoUnicoRepository digitoUnicoRepository) {
		this.digitoUnicoRepository = digitoUnicoRepository;
	}
	
	@Override
	public List<DigitoUnico> buscarTodos()  {
		List<DigitoUnico> digitos = digitoUnicoRepository.findAll();
		if(digitos.isEmpty())
		{
			throw new DigitoNaoEncontradoException(DIGITOS_NAO_ENCONTRADOS);
		}
		
		return digitos;
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
