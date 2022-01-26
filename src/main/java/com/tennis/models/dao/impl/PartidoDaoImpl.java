package com.tennis.models.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tennis.models.dao.IPartidoDao;
import com.tennis.models.entity.Partido;

@Repository("partidoDaoImpl")
public class PartidoDaoImpl implements IPartidoDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(Partido partido) {
		em.persist(partido);

	}

}
