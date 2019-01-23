package com.qwertysatan.webex.service;

import com.qwertysatan.webex.domain.Moron;
import com.qwertysatan.webex.repository.MoronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created by vladislav.uvarov on 23.01.2019.
 */
@Service
@Transactional
public class MoronService {

	@Autowired
	private MoronRepository moronRepository;

	public Moron visit(String name){
		if(StringUtils.isEmpty(name)){
			return moronRepository.findLast();
		}else{
			return registerVisit(name);
		}
	}

	private Moron registerVisit(String name){
		Moron moron = moronRepository.findByName(name);
		if(moron == null){
			moron = new Moron();
			moron.setName(name);
			moron.setVisitTime(new Date());
			moronRepository.persist(moron);
		}else{
			moron.setVisitTime(new Date());
			moronRepository.merge(moron);
		}
		return moron;
	}



}
