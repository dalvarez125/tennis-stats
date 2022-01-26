package com.tennis.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="jugadores")
public class Jugador implements Serializable{
	
	@Id
	@NotNull
	private Long id;
	
	
	//Para Strings, validacion de que sea obligatorio
	//Para distinto de Strings, @NotNull
	
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellidos;
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	private static final long serialVersionUID = 1L;

}
