package com.tennis.models.entity.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EstadisticasId  implements Serializable {

	
	@Column(name = "id_jugador")
	private Long idJugador;
	private String superficie;
	private String nivel;
	
	public Long getIdJugador() {
		return idJugador;
	}
	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}
	public String getSuperficie() {
		return superficie;
	}
	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}
	
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3941234167784061310L;

}
