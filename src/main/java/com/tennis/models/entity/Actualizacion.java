package com.tennis.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="actualizaciones")
public class Actualizacion {
	
	@Id
	@Column(name = "id_actualizacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idActualizacion;
	
	private Integer estado;
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;
	
	
	public Integer getIdActualizacion() {
		return idActualizacion;
	}
	public void setIdActualizacion(Integer idActualizacion) {
		this.idActualizacion = idActualizacion;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
}
