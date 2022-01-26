package com.tennis.models.dao;

import java.util.List;

import com.tennis.models.entity.Actualizacion;

public interface IActualizacionDao {
	
	public List<Actualizacion> findAll();
	
	public boolean actualizacionEnProceso();
	
	public Actualizacion getUltimaActualizacion();
	
	public  Actualizacion save(Actualizacion actualizacion);
	
	public  void update(Actualizacion actualizacion);
	
	public void actualizarTablaEstadisticas();

}
