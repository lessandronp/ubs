package br.com.lessandro.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lessandro.model.Ubs;
import br.com.lessandro.service.IUbsService;

@Service
public class DBService {

	@Autowired
	private IUbsService ubsService;
	
	public void instantiateDatabase() throws ParseException {
		List<Ubs> ubsList = ubsService.readUbsFromCSV(getClass().getClassLoader().getResourceAsStream("ubs.csv"));
		ubsService.saveUbsList(ubsList);
	}
}
