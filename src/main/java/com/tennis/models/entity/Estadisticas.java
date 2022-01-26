package com.tennis.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tennis.models.entity.id.EstadisticasId;

@Entity
@Table(name="estadisticas")
public class Estadisticas implements Serializable{

	@EmbeddedId
	private EstadisticasId id;
	
	private Integer campeon;
	@Column(name = "final")
	private Integer finalista;
	private Integer semifinal;
	@Column(name = "cuartos_final")
	private Integer cuartosFinal;
	private Integer participaciones;
	
	@ManyToOne
    @JoinColumn(name="id_jugador", insertable = false, updatable = false)
	private Jugador jugador;
	
	
	public EstadisticasId getId() {
		return id;
	}
	public void setId(EstadisticasId id) {
		this.id = id;
	}
	public Integer getCampeon() {
		return campeon;
	}
	public void setCampeon(Integer campeon) {
		this.campeon = campeon;
	}
	public Integer getFinalista() {
		return finalista;
	}
	public void setFinalista(Integer finalista) {
		this.finalista = finalista;
	}
	public Integer getSemifinal() {
		return semifinal;
	}
	public void setSemifinal(Integer semifinal) {
		this.semifinal = semifinal;
	}
	public Integer getCuartosFinal() {
		return cuartosFinal;
	}
	public void setCuartosFinal(Integer cuartosFinal) {
		this.cuartosFinal = cuartosFinal;
	}
	public Integer getParticipaciones() {
		return participaciones;
	}
	public void setParticipaciones(Integer participaciones) {
		this.participaciones = participaciones;
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8913741793947142474L;

}
