package com.tennis.models.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tennis.models.dao.IActualizacionDao;
import com.tennis.models.entity.Actualizacion;

@Repository("actualizacionDaoImpl")
public class ActualizacionDaoImpl implements IActualizacionDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Actualizacion> findAll() {
		return em.createQuery("from Actualizacion order by fechaActualizacion desc").getResultList();
	}

	@Override
	@Transactional
	public Actualizacion save(Actualizacion actualizacion) {
		em.persist(actualizacion);
		em.flush();
		return actualizacion;
	}

	@Override
	@Transactional
	public void update(Actualizacion actualizacion) {
		em.merge(actualizacion);
		
	}

	@Override
	public Actualizacion getUltimaActualizacion() {
		return (Actualizacion) em.createQuery("from Actualizacion order by fechaActualizacion desc").getResultList().get(0);
	}

	@Override
	public void actualizarTablaEstadisticas() {
		StoredProcedureQuery query = em.createStoredProcedureQuery("rellenar_estadisticas_atp");
		query.execute();
		StoredProcedureQuery query2 = em.createStoredProcedureQuery("rellenar_estadisticas_challenger");
		query2.execute();
	}

	@Override
	public boolean actualizacionEnProceso() {
		return !em.createQuery("from Actualizacion where estado = 0").getResultList().isEmpty();
	}

}
