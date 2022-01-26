package com.tennis.models.entity.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PartidoId  implements Serializable {
	
	private Integer anio;
	@Column(name = "id_torneo")
	private Long idTorneo;
	@Column(name = "id_partido")
	private Integer idPartido;
	
	

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



	public Integer getIdPartido() {
		return idPartido;
	}



	public void setIdPartido(Integer idPartido) {
		this.idPartido = idPartido;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 7845174136484926061L;

}
