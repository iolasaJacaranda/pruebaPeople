package com.jacaranda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jacaranda.model.People;
import com.jacaranda.repository.PeopleRepository;

@Service
public class PeopleService {
	
	@Autowired
	private PeopleRepository peopleRepository;

	public List<People> getPeoples(){
		
		
		return peopleRepository.findAll();
	}
	
	public void  delPeople(People p) throws Exception {
		this.peopleRepository.delete(p);
		
	}
	
	public People addPeople(People p) {
		People result = null;
		try {
			result = peopleRepository.save(p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public People getPeople(Integer i) throws Exception {
		People p = new People();
		p = peopleRepository.findById(i).orElse(null);
		if (p  == null) throw new Exception("no encontrado");
		return p;
		
	
	}
	
	public People editPeople(People p) throws Exception {
		this.getPeople(p.getId());
		People result = this.peopleRepository.save(p);
		return result;
	}
}
