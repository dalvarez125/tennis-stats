package com.tennis.models.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tennis.models.dao.IJugadorDao;
import com.tennis.models.entity.Jugador;

@Repository("jugadorDaoImpl")
public class JugadorDaoImpl implements IJugadorDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Jugador> findAll() {
		return em.createQuery("from Jugador").getResultList();
	}

	@Override
	@Transactional
	public void save(Jugador jugador) {
		em.persist(jugador);
	}

	@Override
	public Jugador findById(Long id) {
		return em.find(Jugador.class, id);
	}

}
