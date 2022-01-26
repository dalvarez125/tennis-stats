package com.tennis.models.dao;

import java.util.List;

import com.tennis.models.entity.Jugador;

public interface IJugadorDao {
	
	public List<Jugador> findAll();
	
	public  void save(Jugador jugador);
	
	public Jugador findById(Long id);

}
