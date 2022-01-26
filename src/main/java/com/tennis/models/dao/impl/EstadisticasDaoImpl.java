package com.tennis.models.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tennis.models.dao.IEstadisticasDao;
import com.tennis.models.entity.Estadisticas;

@Repository("estadisticasDaoImpl")
public class EstadisticasDaoImpl implements IEstadisticasDao {

	@PersistenceContext
	private EntityManager em;
	
	private static final String query = "from Estadisticas where "
			+ "superficie=:superficie and nivel=:nivel order by campeon desc, finalista desc, semifinal desc, participaciones asc, cuartosFinal desc";

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Estadisticas> findOrdenadosSuperficie(String superficie, String nivel) {
		return em.createQuery(query).setParameter("superficie", superficie).setParameter("nivel", nivel).getResultList();
	}

}
