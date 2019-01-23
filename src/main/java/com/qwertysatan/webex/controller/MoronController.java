package com.qwertysatan.webex.controller;

import com.qwertysatan.webex.domain.Moron;
import com.qwertysatan.webex.service.MoronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vladislav.uvarov on 21.01.2019.
 */
@RestController
public class MoronController {

	@Autowired
	private MoronService moronService;

	@RequestMapping("/moron")
	public Moron test(@RequestParam(value = "name", required = false) String name) {
		try{
			return moronService.visit(name);
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
