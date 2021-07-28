package com.juan.inter.digitoUnico.digitoUnico.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DigitoUnicoDto {

	private Long id;

	@NotNull
	@Min(1)
	private Integer numero;
	
	@Min(1)
	private Integer multiplicador;

	private String resultado;
	
	@Min(1)
	private Long idUsuario;

	public DigitoUnicoDto(Integer numero, Integer multiplicador, String resultado, Long idUsuario) {
		this.numero = numero;
		this.multiplicador = multiplicador;
		this.resultado = resultado;
		this.idUsuario = idUsuario;
	}

}
