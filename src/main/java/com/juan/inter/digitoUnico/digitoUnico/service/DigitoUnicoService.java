package com.juan.inter.digitoUnico.digitoUnico.service;

import java.util.List;

import com.juan.inter.digitoUnico.digitoUnico.model.DigitoUnico;

public interface DigitoUnicoService {
	
	 DigitoUnico salvar(DigitoUnico digitoUnico);
	 
	 DigitoUnico calcularDigito(Integer numero, Integer multiplo);
	 
	 List<DigitoUnico> ultimosResultados(int q);

}
