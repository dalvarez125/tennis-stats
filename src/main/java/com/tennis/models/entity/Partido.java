package com.tennis.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tennis.models.entity.id.PartidoId;

@Entity
@Table(name="partidos")
public class Partido implements Serializable{
	
	@EmbeddedId
	private PartidoId id;
	
	@Column(name = "id_ganador")
	private Long idGanador;
	@Column(name = "id_perdedor")
	private Long idPerdedor;
	private String ronda;
	private String resultado;
	

	public PartidoId getId() {
		return id;
	}



	public void setId(PartidoId id) {
		this.id = id;
	}



	public Long getIdGanador() {
		return idGanador;
	}



	public void setIdGanador(Long idGanador) {
		this.idGanador = idGanador;
	}



	public Long getIdPerdedor() {
		return idPerdedor;
	}



	public void setIdPerdedor(Long idPerdedor) {
		this.idPerdedor = idPerdedor;
	}



	public String getRonda() {
		return ronda;
	}



	public void setRonda(String ronda) {
		this.ronda = ronda;
	}



	public String getResultado() {
		return resultado;
	}



	public void setResultado(String resultado) {
		this.resultado = resultado;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 2568847802552784020L;
	
	

}
