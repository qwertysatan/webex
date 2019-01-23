package com.qwertysatan.webex.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by vladislav.uvarov on 23.01.2019.
 */
public abstract class AbstractRepository<T> {

	@PersistenceContext
	protected EntityManager em;

	protected Class<T> entityClass;

	public AbstractRepository(Class<T> entityClass){
		this.entityClass = entityClass;
	}

	public T find(long id){
		return em.find(entityClass, id);
	}

	public void persist(T entity){
		em.persist(entity);
	}

	public void merge(T entity){
		em.merge(entity);
	}

	public void remove(T entity){
		throw new IllegalStateException("Removing from database is forbidden");
	}

}
