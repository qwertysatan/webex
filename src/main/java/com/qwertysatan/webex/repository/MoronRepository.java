package com.qwertysatan.webex.repository;

import com.qwertysatan.webex.domain.Moron;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Created by vladislav.uvarov on 23.01.2019.
 */
@Repository
public class MoronRepository extends AbstractRepository<Moron> {

	public MoronRepository(){
		super(Moron.class);
	}

	public Moron findLast(){
		TypedQuery<Moron> query = em.createQuery("SELECT m FROM com.qwertysatan.webex.domain.Moron m ORDER BY m.visitTime DESC", entityClass);
		query.setMaxResults(1);
		try{
			return query.getSingleResult();
		}catch (NoResultException e){
			return new Moron();
		}
	}

	public Moron findByName(String name){
		TypedQuery<Moron> query = em.createQuery("SELECT m FROM com.qwertysatan.webex.domain.Moron m WHERE upper(m.name) = upper(:name) ", entityClass);
		query.setParameter("name", name);
		try{
			return query.getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}


}
