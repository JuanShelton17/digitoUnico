package com.juan.inter.digitoUnico.digitoUnico.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juan.inter.digitoUnico.digitoUnico.model.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto {

	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String email;

	@JsonProperty("digitosCalculados")
	private List<DigitoUnicoDto> digitoUnicoDtoList;

	public Usuario toEntity() {
		Usuario usuario = new Usuario();
		usuario.setId(this.id);
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);

		return usuario;
	}
}
