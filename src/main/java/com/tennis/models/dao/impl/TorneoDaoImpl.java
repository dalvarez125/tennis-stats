package com.tennis.models.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tennis.models.dao.ITorneoDao;
import com.tennis.models.entity.Torneo;

@Repository("torneoDaoImpl")
public class TorneoDaoImpl implements ITorneoDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(Torneo torneo) {
		em.persist(torneo);

	}

}
