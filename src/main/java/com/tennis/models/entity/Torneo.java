package com.tennis.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tennis.models.entity.id.TorneoId;

@Entity
@Table(name="torneos")
public class Torneo  implements Serializable{
	
	@EmbeddedId
	private TorneoId id;
	
	private String nombre;
	private String superficie;
	@Column(name = "numero_jugadores")
	private Integer numeroJugadores;
	private String nivel;
	@Column(name = "fecha_final")
	private Date fechaFinal;
	private Integer sets;
	
	
	
	public TorneoId getId() {
		return id;
	}



	public void setId(TorneoId id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getSuperficie() {
		return superficie;
	}



	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}



	public Integer getNumeroJugadores() {
		return numeroJugadores;
	}



	public void setNumeroJugadores(Integer numeroJugadores) {
		this.numeroJugadores = numeroJugadores;
	}



	public String getNivel() {
		return nivel;
	}



	public void setNivel(String nivel) {
		this.nivel = nivel;
	}



	public Date getFechaFinal() {
		return fechaFinal;
	}



	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}



	public Integer getSets() {
		return sets;
	}



	public void setSets(Integer sets) {
		this.sets = sets;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = -4129170797517084011L;
	

}
