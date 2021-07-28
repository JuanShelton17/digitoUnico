package com.juan.inter.digitoUnico.digitoUnico.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.juan.inter.digitoUnico.digitoUnico.dto.DigitoUnicoDto;
import com.juan.inter.digitoUnico.digitoUnico.dto.UsuarioDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="digitoUnico")
@Getter @Setter @NoArgsConstructor
public class DigitoUnico implements Serializable {
	
	 	@Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    public Long id;

	    @Column
	    public Integer numero;

	    @Column
	    public Integer multiplicador;

	    @Column
	    public String resultado;

	    @NotNull
	    @ManyToOne(fetch = FetchType.EAGER)
	    public Usuario usuario;
	    
	    public DigitoUnico(Integer numero, Integer multiplicador, String resultado) {
	        this.numero = numero;
	        this.multiplicador = multiplicador;
	        this.resultado = resultado;
	    }
	    
	    public Usuario getUsuario() {
	        return usuario == null ? new Usuario() : usuario;
	    }
	    
	    public DigitoUnicoDto toDto() {
	    	DigitoUnicoDto digitoUnicoDto =  new DigitoUnicoDto();
	    	digitoUnicoDto.setId(this.id);
	    	digitoUnicoDto.setNumero(this.numero);
	        digitoUnicoDto.setMultiplicador(this.multiplicador);
	        digitoUnicoDto.setResultado(this.resultado);
	        digitoUnicoDto.setIdUsuario(this.getUsuario().getId());
	        return digitoUnicoDto;
		}

}
