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
	    @JoinColumn(name = "usuario_id")
	    public Usuario usuario;

}
