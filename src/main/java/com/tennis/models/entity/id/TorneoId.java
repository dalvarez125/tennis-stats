package com.tennis.models.entity.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TorneoId  implements Serializable{
	
	private Integer anio;
	@Column(name = "id_torneo")
	private Long idTorneo;
	
	

	public Integer getAnio() {
		return anio;
	}



	public void setAnio(Integer anio) {
		this.anio = anio;
	}



	public Long getIdTorneo() {
		return idTorneo;
	}



	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = -6488803074583850138L;

}
