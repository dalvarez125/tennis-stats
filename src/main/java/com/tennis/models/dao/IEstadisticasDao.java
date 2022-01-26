package com.tennis.models.dao;

import java.util.List;

import com.tennis.models.entity.Estadisticas;

public interface IEstadisticasDao {
	
	public List<Estadisticas> findOrdenadosSuperficie(String superficie, String nivel);

}
