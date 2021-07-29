package com.juan.inter.digitoUnico.digitoUnico.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.juan.inter.digitoUnico.digitoUnico.dto.UsuarioDto;
import com.juan.inter.digitoUnico.digitoUnico.util.DigitoUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter @Setter @NoArgsConstructor
public class Usuario implements Serializable {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<DigitoUnico> digitoUnicoList = new ArrayList<>();;

	public Usuario(Long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public Usuario(Long id) {
        this.id = id;
    }
	
	public UsuarioDto toDto() {
		UsuarioDto usuarioDto =  new UsuarioDto();
		usuarioDto.setId(this.id);
        usuarioDto.setNome(this.nome);
        usuarioDto.setEmail(this.email);
        usuarioDto.setDigitoUnicoDtoList(DigitoUtils.toListDto(this.digitoUnicoList));
		return usuarioDto;
	}
}
